package team.tripbeats.backend.domain.post.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import team.tripbeats.backend.domain.post.dto.CommentCreateDto;
import team.tripbeats.backend.domain.post.dto.CommentDto;
import team.tripbeats.backend.domain.post.entity.Comment;
import team.tripbeats.backend.domain.post.entity.Post;
import team.tripbeats.backend.domain.post.repository.CommentRepository;
import team.tripbeats.backend.domain.post.repository.PostRepository;
import team.tripbeats.backend.entity.Account;
import team.tripbeats.backend.repository.AccountRepository;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CommentService {
    private final CommentRepository commentRepository;
    private final AccountRepository accountRepository;
    private final PostRepository postRepository;

    // 댓글 생성 (CommentCreateDto 사용)
    public CommentDto createComment(CommentCreateDto commentCreateDto) {
        Account account = accountRepository.findById(commentCreateDto.getAccountId())
                .orElseThrow(() -> new IllegalArgumentException("Account not found"));
        Post post = postRepository.findById(commentCreateDto.getPostId())
                .orElseThrow(() -> new IllegalArgumentException("Post not found"));

        Comment comment = Comment.builder()
                .content(commentCreateDto.getContent())
                .post(post)
                .account(account)
                .build();

        Comment savedComment = commentRepository.save(comment);
        return convertToDto(savedComment);
    }

    // 특정 게시글의 댓글 조회
    public List<CommentDto> getCommentsByPost(Long postId) {
        List<Comment> comments = commentRepository.findByPostId(postId);
        return comments.stream().map(this::convertToDto).toList();
    }

    // Comment -> CommentDto 변환
    private CommentDto convertToDto(Comment comment) {
        return CommentDto.builder()
                .id(comment.getId())
                .content(comment.getContent())
                .accountName(comment.getAccount().getKakaoName())  // 작성자 이름 포함
                .build();
    }
}
