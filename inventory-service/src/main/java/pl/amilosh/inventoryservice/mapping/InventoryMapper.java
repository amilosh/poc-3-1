package pl.amilosh.inventoryservice.mapping;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import pl.amilosh.inventoryservice.dto.InventoryDto;
import pl.amilosh.inventoryservice.model.Inventory;

import java.util.List;

@Mapper
public interface InventoryMapper {

    @Mapping(target = "id", ignore = true)
    Inventory toEntity(InventoryDto inventoryDto);

    List<Inventory> toEntities(List<InventoryDto> inventoryDtos);

    InventoryDto toDto(Inventory inventory);

    List<InventoryDto> toDtos(List<Inventory> inventories);
}
