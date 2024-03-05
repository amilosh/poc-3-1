package pl.amilosh.inventoryservice.service;

import pl.amilosh.inventoryservice.dto.InventoryDto;

import java.util.List;

public interface InventoryService {

    void createInventory(InventoryDto inventoryDto);

    List<InventoryDto> isInStock(List<String> skuCode);
}
