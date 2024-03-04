package pl.amilosh.orderservice.service;

import pl.amilosh.orderservice.dto.OrderDto;

public interface OrderService {

    OrderDto createOrder(OrderDto orderDto);
}
