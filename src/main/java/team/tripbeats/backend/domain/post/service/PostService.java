package team.tripbeats.backend.domain.post.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import team.tripbeats.backend.domain.post.dto.CommentDto;
import team.tripbeats.backend.domain.post.dto.PostCreateDto;
import team.tripbeats.backend.domain.post.dto.PostDetailDto;
import team.tripbeats.backend.domain.post.dto.PostSimpleDto;
import team.tripbeats.backend.domain.post.entity.Comment;
import team.tripbeats.backend.domain.post.entity.Post;
import team.tripbeats.backend.domain.post.repository.CommentRepository;
import team.tripbeats.backend.domain.post.repository.PostRepository;
import team.tripbeats.backend.entity.Account;
import team.tripbeats.backend.repository.AccountRepository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class PostService {

    private final PostRepository postRepository;
    private final AccountRepository accountRepository;
    private final CommentRepository commentRepository;

    public void createPost(PostCreateDto postCreateDto) {
        Account account = accountRepository.findById(postCreateDto.getAccountId()).orElseThrow(() -> new RuntimeException("Account not found"));

        LocalDateTime timestamp = LocalDateTime.now();  // 현재 시간 생성

        Post post = Post.builder()
                .title(postCreateDto.getTitle())
                .content(postCreateDto.getContent())
                .category(postCreateDto.getCategory())
                .location(postCreateDto.getLocation())
                .schedule(postCreateDto.getSchedule())
                .image(postCreateDto.getImage())
                .account(account)                // 작성자 정보 설정
                .timestamp(timestamp)            // 현재 시간 설정
                .build();

        postRepository.save(post);  // 게시글 저장
    }

    // 게시글 수정
    public void updatePost(Long postId, PostCreateDto postCreateDto) {
        Post post = postRepository.findById(postId).orElseThrow(() -> new RuntimeException("Post not found"));

        LocalDateTime timestamp = LocalDateTime.now();

        // accountId로 작성자가 동일한지 확인 (옵션)
        if (!post.getAccount().getId().equals(postCreateDto.getAccountId())) {
            throw new RuntimeException("Only the author can update the post.");
        }

        // 게시글 덮어씌우기
        post.setTitle(postCreateDto.getTitle());
        post.setContent(postCreateDto.getContent());
        post.setCategory(postCreateDto.getCategory());
        post.setLocation(postCreateDto.getLocation());
        post.setSchedule(postCreateDto.getSchedule());
        post.setImage(postCreateDto.getImage());
        post.setTimestamp(timestamp);

        postRepository.save(post);  // 수정된 게시글 저장
    }

    public void deletePost(Long postId) {
        Post post = postRepository.findById(postId).orElseThrow(() -> new RuntimeException("Post not found"));
        postRepository.delete(post);  // 게시글 삭제, 관련 댓글도 함께 삭제됨 (CascadeType.REMOVE)
    }

    public List<PostSimpleDto> getAllSimplePosts() {
        return postRepository.findAll().stream().map(post -> {
            int commentCount = commentRepository.countByPost(post);  // 댓글 수 계산

            return PostSimpleDto.builder()
                    .postId(post.getId())
                    .title(post.getTitle())
                    .category(post.getCategory())
                    .location(post.getLocation())
                    .schedule(post.getSchedule())
                    .timestamp(post.getTimestamp())
                    .image(post.getImage())          // Base64 이미지
                    .comments(commentCount)          // 댓글 수 추가
                    .build();
        }).collect(Collectors.toList());
    }

    public PostDetailDto getPostDetailById(Long postId) {
        Post post = postRepository.findById(postId).orElseThrow(() -> new RuntimeException("Post not found"));
        List<Comment> comments = commentRepository.findByPost(post);

        List<CommentDto> commentDtos = comments.stream().map(comment ->
                CommentDto.builder()
                        .commentId(comment.getId())
                        .userId(comment.getAccount().getId())
                        .kakaoName(comment.getAccount().getKakaoName())
                        .content(comment.getContent())
                        .timestamp(comment.getTimestamp())
                        .build()
        ).collect(Collectors.toList());

        return PostDetailDto.builder()
                .postId(post.getId())
                .title(post.getTitle())
                .content(post.getContent())
                .category(post.getCategory())
                .location(post.getLocation())
                .schedule(post.getSchedule())
                .userId(post.getAccount().getId())         // 작성자 ID
                .kakaoName(post.getAccount().getKakaoName()) // 작성자 이름
                .image(post.getImage())
                .timestamp(post.getTimestamp())
                .totalCount(commentDtos.size())            // 댓글 총 개수
                .commentList(commentDtos)                  // 댓글 리스트
                .build();
    }
}
