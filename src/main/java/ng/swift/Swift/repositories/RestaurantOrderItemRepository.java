package ng.swift.Swift.repositories;

import ng.swift.Swift.models.RestaurantOrderItem;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RestaurantOrderItemRepository extends JpaRepository<RestaurantOrderItem, Long> {
}
