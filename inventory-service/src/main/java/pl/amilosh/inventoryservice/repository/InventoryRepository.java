package pl.amilosh.inventoryservice.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.amilosh.inventoryservice.model.Inventory;

import java.util.List;

public interface InventoryRepository extends JpaRepository<Inventory, Integer> {

    List<Inventory> findBySkuCodeIn(List<String> skuCode);
}
