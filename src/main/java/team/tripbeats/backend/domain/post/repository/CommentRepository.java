package team.tripbeats.backend.domain.post.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import team.tripbeats.backend.domain.post.entity.Comment;
import team.tripbeats.backend.domain.post.entity.Post;

import java.util.List;

public interface CommentRepository extends JpaRepository<Comment, Long> {
    List<Comment> findByPost(Post post);
    @Query("SELECT COUNT(c) FROM Comment c WHERE c.post = :post")
    int countByPost(Post post);
}
