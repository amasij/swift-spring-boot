package ng.swift.Swift.repositories;

import ng.swift.Swift.models.RestaurantMealCategory;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RestaurantMealCategoryRepository extends JpaRepository<RestaurantMealCategory, Long> {
}
