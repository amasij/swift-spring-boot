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

    @Query("SELECT u FROM User u WHERE u.status = 'ACTIVE' and u.email = ?1")
    Optional<User> findActiveByEmail(String id);

    @Query("SELECT u FROM User u WHERE u.status = 'ACTIVE' and u.phoneNumber = ?1")
    Optional<User> findActiveByPhoneNumber(String phoneNumber);

}
