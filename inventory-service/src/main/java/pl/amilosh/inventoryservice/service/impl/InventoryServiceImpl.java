package pl.amilosh.inventoryservice.service.impl;

import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pl.amilosh.inventoryservice.dto.InventoryDto;
import pl.amilosh.inventoryservice.mapping.InventoryMapper;
import pl.amilosh.inventoryservice.repository.InventoryRepository;
import pl.amilosh.inventoryservice.service.InventoryService;

import java.util.List;

@Service
@RequiredArgsConstructor
public class InventoryServiceImpl implements InventoryService {

    private final InventoryMapper inventoryMapper;
    private final InventoryRepository inventoryRepository;

    @Override
    public void createInventory(InventoryDto inventoryDto) {
        var inventory = inventoryMapper.toEntity(inventoryDto);
        inventoryRepository.save(inventory);
    }

    @Transactional(readOnly = true)
    @SneakyThrows
    public List<InventoryDto> isInStock(List<String> skuCode) {
        var inventories = inventoryRepository.findBySkuCodeIn(skuCode);
        return inventoryMapper.toDtos(inventories);
    }
}
