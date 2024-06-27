package team.tripbeats.backend.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import team.tripbeats.backend.entity.MusicGenre;

import java.util.Optional;

public interface MusicGenreRepository extends JpaRepository<MusicGenre, Long> {
    Optional<MusicGenre> findByName(String name);
}
