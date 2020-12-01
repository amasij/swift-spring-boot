package ng.swift.Swift.repositories;

import ng.swift.Swift.models.Rider;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RiderRepository extends JpaRepository<Rider, Long> {
    @Query(nativeQuery = true, value = "SELECT r.id FROM Rider r" +
            " INNER JOIN delivery_company dc ON r.delivery_company = dc.id" +
            " INNER JOIN address addr ON addr.id=dc.address" +
            " INNER JOIN gpscoordinates gc on addr.gps_cordinate = gc.id" +
            " WHERE earth_distance(ll_to_earth( gc.latitude, gc.longitude ), ll_to_earth(:latitude, :longitude)) <= :radiusInMeters" +
            " ORDER BY earth_distance(ll_to_earth( gc.latitude, gc.longitude ), ll_to_earth(:latitude, :longitude))")
    List<Long> getRidersById(@Param("latitude") Double latitude, @Param("longitude") Double longitude, @Param("radiusInMeters") Double radiusInMeters);

}
