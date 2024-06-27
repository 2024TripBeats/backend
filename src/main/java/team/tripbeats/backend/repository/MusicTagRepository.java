package team.tripbeats.backend.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import team.tripbeats.backend.entity.MusicTag;

import java.util.Optional;

public interface MusicTagRepository extends JpaRepository<MusicTag, Long> {
    Optional<MusicTag> findByName(String name);
}
