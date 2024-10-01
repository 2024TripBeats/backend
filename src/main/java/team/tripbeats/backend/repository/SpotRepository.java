package team.tripbeats.backend.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import team.tripbeats.backend.entity.Spot;

import java.util.Optional;

public interface SpotRepository extends JpaRepository<Spot, String> {
    Optional<Spot> findByVisitAreaNm(String visitAreaNm);
}
