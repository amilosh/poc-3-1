package pl.amilosh.orderservice.mapping;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import pl.amilosh.orderservice.dto.OrderLineItemDto;
import pl.amilosh.orderservice.model.OrderLineItem;

import java.util.List;

@Mapper
public interface OrderLineItemMapper {

    @Mapping(target = "id", ignore = true)
    OrderLineItem toEntity(OrderLineItemDto orderLineItemDto);

    List<OrderLineItem> toEntities(List<OrderLineItemDto> orderLineItemDtos);

    OrderLineItemDto toDto(OrderLineItem orderLineItem);

    List<OrderLineItemDto> toDtos(List<OrderLineItem> orderLineItems);
}
