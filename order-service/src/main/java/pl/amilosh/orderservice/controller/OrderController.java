package pl.amilosh.orderservice.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import pl.amilosh.orderservice.dto.OrderDto;
import pl.amilosh.orderservice.service.OrderService;

import static org.springframework.http.HttpStatus.CREATED;
import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@Slf4j
@RestController
@RequestMapping("/order")
@RequiredArgsConstructor
public class OrderController {

    private final OrderService orderService;

    @PostMapping(produces = APPLICATION_JSON_VALUE)
    @ResponseStatus(CREATED)
    public ResponseEntity<Object> createOrder(@RequestBody OrderDto orderDto) {
        var createdOrder = orderService.createOrder(orderDto);
        return new ResponseEntity<>(createdOrder, CREATED);
    }
}
