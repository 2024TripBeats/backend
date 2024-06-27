package team.tripbeats.backend.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import team.tripbeats.backend.entity.Trip;

import java.util.List;

public interface TripRepository extends JpaRepository<Trip, Integer> {
    List<Trip> findByAccountId(Long accountId);
}
