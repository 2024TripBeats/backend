package team.tripbeats.backend.domain.post.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import team.tripbeats.backend.domain.post.dto.CommentCreateDto;
import team.tripbeats.backend.domain.post.entity.Comment;
import team.tripbeats.backend.domain.post.entity.Post;
import team.tripbeats.backend.domain.post.repository.CommentRepository;
import team.tripbeats.backend.domain.post.repository.PostRepository;
import team.tripbeats.backend.entity.Account;
import team.tripbeats.backend.repository.AccountRepository;

import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
public class CommentService {

    private final CommentRepository commentRepository;
    private final PostRepository postRepository;
    private final AccountRepository accountRepository;

    // 리턴값을 없앤 댓글 생성 메서드
    public void createComment(CommentCreateDto commentCreateDto) {
        Post post = postRepository.findById(commentCreateDto.getPostId()).orElseThrow(() -> new RuntimeException("Post not found"));
        Account account = accountRepository.findById(commentCreateDto.getAccountId()).orElseThrow(() -> new RuntimeException("Account not found"));

        LocalDateTime timestamp = LocalDateTime.now();

        Comment comment = Comment.builder()
                .content(commentCreateDto.getContent())
                .post(post)
                .account(account)
                .timestamp(timestamp)
                .build();

        commentRepository.save(comment);  // 댓글 저장만 수행
    }

    public void deleteComment(Long commentId) {
        Comment comment = commentRepository.findById(commentId).orElseThrow(() -> new RuntimeException("Comment not found"));
        commentRepository.delete(comment);  // 댓글 삭제
    }
}
