package ng.swift.Swift.repositories;

import ng.swift.Swift.models.Preference;
import ng.swift.Swift.models.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface PreferenceRepository extends JpaRepository<Preference, Long> {
    @Query("SELECT p FROM Preference p JOIN FETCH p.mealCategory WHERE p.status = 'ACTIVE' AND p.user = ?1 ")
    List<Preference> getActivePreferencesByUser(User user);
}
