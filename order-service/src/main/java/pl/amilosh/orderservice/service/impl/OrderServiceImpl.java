package pl.amilosh.orderservice.service.impl;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.collections4.CollectionUtils;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.reactive.function.client.WebClient;
import pl.amilosh.orderservice.dto.InventoryDto;
import pl.amilosh.orderservice.dto.OrderDto;
import pl.amilosh.orderservice.event.OrderSavedEvent;
import pl.amilosh.orderservice.mapping.OrderMapper;
import pl.amilosh.orderservice.model.Order;
import pl.amilosh.orderservice.model.OrderLineItem;
import pl.amilosh.orderservice.repository.OrderRepository;
import pl.amilosh.orderservice.service.OrderService;

import java.util.Arrays;

import static org.springframework.http.MediaType.APPLICATION_JSON;

@Slf4j
@Service
@Transactional
@RequiredArgsConstructor
public class OrderServiceImpl implements OrderService {

    private final OrderMapper orderMapper;
    private final OrderRepository orderRepository;
    private final WebClient.Builder webClientBuilder;
    private final KafkaTemplate<String, OrderSavedEvent> kafkaTemplate;

    @Override
    public OrderDto createOrder(OrderDto orderDto) {
        log.info("Create order");
        var order = orderMapper.toEntity(orderDto);
        validateOrder(order);
        var savedOrder = orderRepository.save(order);
        kafkaTemplate.send("notificationTopic", new OrderSavedEvent(savedOrder.getOrderNumber()));
        return orderMapper.toDto(savedOrder);
    }

    private void validateOrder(Order order) {
        var orderSkus = order.getOrderLineItems().stream()
            .map(OrderLineItem::getSkuCode).toList();

        var inventories = webClientBuilder.build().get()
            .uri("http://inventory-service/inventory",
                uriBuilder -> uriBuilder.queryParam("skuCode", orderSkus).build())
            .accept(APPLICATION_JSON)
            .retrieve()
            .bodyToMono(InventoryDto[].class).block();

        if (inventories == null) {
            throw new IllegalArgumentException("User placed order with item that doesn't exist in inventory store");
        }

        var inventoriesSkus = Arrays.stream(inventories)
            .map(InventoryDto::getSkuCode).toList();

        if (!CollectionUtils.isEqualCollection(orderSkus, inventoriesSkus)) {
            throw new IllegalArgumentException("User placed order with item that doesn't exist in inventory store");
        }

        var itemWithoutInventoryExists = Arrays.stream(inventories)
            .anyMatch(inventory -> inventory.getQuantity() == 0);

        if (itemWithoutInventoryExists) {
            throw new IllegalArgumentException("User placed order with item that has quantity 0");
        }
    }
}