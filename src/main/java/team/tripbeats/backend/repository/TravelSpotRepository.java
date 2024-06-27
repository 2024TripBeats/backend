package team.tripbeats.backend.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import team.tripbeats.backend.entity.TravelSpot;

import java.util.Optional;

public interface TravelSpotRepository extends JpaRepository<TravelSpot, Long> {
    Optional<TravelSpot> findBySpotName(String spotName);
}
