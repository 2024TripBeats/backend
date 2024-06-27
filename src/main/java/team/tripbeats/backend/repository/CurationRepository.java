package team.tripbeats.backend.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import team.tripbeats.backend.entity.Curation;

public interface CurationRepository extends JpaRepository<Curation, Long> {
}
