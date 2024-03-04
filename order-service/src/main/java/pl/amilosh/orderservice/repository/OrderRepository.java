package pl.amilosh.orderservice.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.amilosh.orderservice.model.Order;

public interface OrderRepository extends JpaRepository<Order, Integer> {
}
