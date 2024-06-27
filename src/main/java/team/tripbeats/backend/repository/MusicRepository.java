package team.tripbeats.backend.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import team.tripbeats.backend.entity.Music;

public interface MusicRepository extends JpaRepository<Music, Integer> {
}
