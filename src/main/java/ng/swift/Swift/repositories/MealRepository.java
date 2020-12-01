package ng.swift.Swift.repositories;

import ng.swift.Swift.models.Meal;
import ng.swift.Swift.models.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;

import java.util.List;
import java.util.Optional;

public interface MealRepository extends JpaRepository<Meal, Long> {
    @Query("SELECT m FROM Meal m WHERE m.status = 'ACTIVE' and m.id = ?1")
    Optional<Meal> findActiveById(Long id);


    @Query("SELECT m FROM Meal m WHERE m.status = 'ACTIVE' and m.id in ?1")
    List<Meal> findActiveByIds(List<Long> ids);
}
