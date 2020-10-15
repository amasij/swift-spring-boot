package ng.swift.Swift.repositories;

import ng.swift.Swift.models.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    @Query("SELECT u FROM User u WHERE u.status = 'ACTIVE' and u.id = ?1")
    Optional<User> findActiveById(Long id);
}
