package ng.swift.Swift.repositories;

import ng.swift.Swift.models.Restaurant;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface RestaurantRepository extends JpaRepository<Restaurant, Long> {
    @Query("SELECT r FROM Restaurant r WHERE r.status = 'ACTIVE' AND r.id = ?1")
    Optional<Restaurant> findActiveById(long id);
}
