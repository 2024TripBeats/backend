package team.tripbeats.backend.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import team.tripbeats.backend.entity.TripDaySpot;

public interface TripDaySpotRepository extends JpaRepository<TripDaySpot, Integer> {
}
