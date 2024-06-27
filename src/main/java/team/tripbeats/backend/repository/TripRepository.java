package team.tripbeats.backend.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import team.tripbeats.backend.entity.Trip;

public interface TripRepository extends JpaRepository<Trip, Integer> {

}
