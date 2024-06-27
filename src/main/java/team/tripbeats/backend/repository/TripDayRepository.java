package team.tripbeats.backend.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import team.tripbeats.backend.entity.TripDay;

public interface TripDayRepository extends JpaRepository<TripDay, Long> {
}
