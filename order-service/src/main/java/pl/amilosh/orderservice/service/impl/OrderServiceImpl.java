package pl.amilosh.orderservice.service.impl;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pl.amilosh.orderservice.dto.OrderDto;
import pl.amilosh.orderservice.mapping.OrderMapper;
import pl.amilosh.orderservice.repository.OrderRepository;
import pl.amilosh.orderservice.service.OrderService;

@Slf4j
@Service
@Transactional
@RequiredArgsConstructor
public class OrderServiceImpl implements OrderService {

    private final OrderMapper orderMapper;
    private final OrderRepository orderRepository;

    @Override
    public OrderDto createOrder(OrderDto orderDto) {
        log.info("Create order");
        var order = orderMapper.toEntity(orderDto);
        var savedOrder = orderRepository.save(order);
        return orderMapper.toDto(savedOrder);
    }
}