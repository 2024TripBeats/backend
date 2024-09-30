package team.tripbeats.backend.domain.FlightFare.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import team.tripbeats.backend.domain.FlightFare.entity.FlightFare;

import java.util.List;

public interface FlightFareRepository extends JpaRepository<FlightFare, Long> {
    List<FlightFare> findByRouteAndTimeLabel(String route, String timeLabel);
    List<FlightFare> findByYearAndMonthAndDayAndRouteAndTimeLabel(int year, int month, int day, String route, String timeLabel);
}
