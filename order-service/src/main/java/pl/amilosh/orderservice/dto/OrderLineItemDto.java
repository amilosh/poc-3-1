package pl.amilosh.orderservice.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
public class OrderLineItemDto {

    private Integer id;
    private String skuCode;
    private BigDecimal price;
    private Integer quantity;
}
