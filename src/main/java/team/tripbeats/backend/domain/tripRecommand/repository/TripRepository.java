package team.tripbeats.backend.domain.tripRecommand.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import team.tripbeats.backend.domain.tripRecommand.entity.Trip;

import java.util.List;

public interface TripRepository extends JpaRepository<Trip, Long> {
    List<Trip> findByAccountId(Long accountId);
}
