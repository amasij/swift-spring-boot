package ng.swift.Swift.repositories;

import ng.swift.Swift.models.RestaurantOrderItem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface RestaurantOrderItemRepository extends JpaRepository<RestaurantOrderItem, Long> {
    @Query(" SELECT r FROM RestaurantOrderItem r WHERE r.id = ?1 ")
    Optional<RestaurantOrderItem> findActiveById(Long id);
}
