package team.tripbeats.backend.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import team.tripbeats.backend.entity.Spot;

public interface SpotRepository extends JpaRepository<Spot, Integer> {
}
