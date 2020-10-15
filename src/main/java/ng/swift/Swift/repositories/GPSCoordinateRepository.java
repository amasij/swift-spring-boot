package ng.swift.Swift.repositories;

import ng.swift.Swift.models.GPSCoordinate;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface GPSCoordinateRepository extends JpaRepository<GPSCoordinate, Long> {
}

