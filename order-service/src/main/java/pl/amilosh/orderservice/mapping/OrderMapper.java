package pl.amilosh.orderservice.mapping;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import pl.amilosh.orderservice.dto.OrderDto;
import pl.amilosh.orderservice.model.Order;

@Mapper(
    uses = OrderLineItemMapper.class,
    imports = java.util.UUID.class)
public interface OrderMapper {

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "orderNumber", expression = "java( UUID.randomUUID().toString() )")
    @Mapping(target = "orderLineItems", source = "orderDto.orderLineItemDtos")
    Order toEntity(OrderDto orderDto);

    @Mapping(target = "orderLineItemDtos", source = "order.orderLineItems")
    OrderDto toDto(Order order);
}
