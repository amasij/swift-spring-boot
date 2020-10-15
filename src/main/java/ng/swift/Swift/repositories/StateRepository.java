package ng.swift.Swift.repositories;

import ng.swift.Swift.models.State;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface StateRepository extends JpaRepository<State, Long> {
    @Query("SELECT s FROM State s WHERE s.code = ?1")
    Optional<State> findByCode(String code);
}
