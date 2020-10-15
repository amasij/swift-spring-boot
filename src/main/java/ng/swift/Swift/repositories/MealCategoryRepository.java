package ng.swift.Swift.repositories;

import ng.swift.Swift.models.MealCategory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface MealCategoryRepository extends JpaRepository<MealCategory, Long> {
    @Query("SELECT mc FROM MealCategory mc WHERE mc.status = 'ACTIVE' AND lower(mc.name) = ?1")
    Optional<MealCategory> findActiveByName(String name);

    @Query("SELECT mc FROM MealCategory mc WHERE mc.status = 'ACTIVE' AND mc.id = ?1 ")
    Optional<MealCategory> findActiveById(Long id);

    @Query("SELECT mc FROM MealCategory mc WHERE mc.status = 'ACTIVE' AND mc.id in ?1 ")
    List<MealCategory> findActiveByIds(List<Long> id);
}
