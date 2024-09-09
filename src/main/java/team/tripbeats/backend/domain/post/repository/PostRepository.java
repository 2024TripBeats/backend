package team.tripbeats.backend.domain.post.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import team.tripbeats.backend.domain.post.entity.Post;

public interface PostRepository extends JpaRepository<Post, Long> {
}
