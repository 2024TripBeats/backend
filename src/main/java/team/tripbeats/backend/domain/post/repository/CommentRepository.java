package team.tripbeats.backend.domain.post.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import team.tripbeats.backend.domain.post.entity.Comment;

import java.util.List;

public interface CommentRepository extends JpaRepository<Comment, Long> {
    List<Comment> findByPostId(Long postId);
}