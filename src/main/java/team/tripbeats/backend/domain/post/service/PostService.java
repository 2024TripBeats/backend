package team.tripbeats.backend.domain.post.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import team.tripbeats.backend.domain.post.dto.PostDetailDto;
import team.tripbeats.backend.domain.post.dto.PostCreateDto;
import team.tripbeats.backend.domain.post.dto.PostSimpleDto;
import team.tripbeats.backend.domain.post.entity.Post;
import team.tripbeats.backend.domain.post.repository.PostRepository;
import team.tripbeats.backend.entity.Account;
import team.tripbeats.backend.repository.AccountRepository;

import java.util.List;

@Service
@RequiredArgsConstructor
public class PostService {
    private final PostRepository postRepository;
    private final AccountRepository accountRepository;

    public PostDetailDto createPost(PostCreateDto postCreateDto, Long accountId) {
        Account account = accountRepository.findById(accountId)
                .orElseThrow(() -> new IllegalArgumentException("Account not found"));

        Post post = Post.builder()
                .title(postCreateDto.getTitle())
                .content(postCreateDto.getContent())
                .account(account)
                .build();

        Post savedPost = postRepository.save(post);
        return convertToDetailDto(savedPost);
    }

    // 게시글 전체 목록 간단히 조회
    public List<PostSimpleDto> getAllSimplePosts() {
        List<Post> posts = postRepository.findAll();
        return posts.stream()
                .map(this::convertToSimpleDto)
                .toList();
    }

    // 게시글 상세 조회
    public PostDetailDto getPostDetailById(Long postId) {
        Post post = postRepository.findById(postId)
                .orElseThrow(() -> new IllegalArgumentException("Post not found"));
        return convertToDetailDto(post);
    }

    // Post -> PostSimpleDto 변환
    private PostSimpleDto convertToSimpleDto(Post post) {
        return PostSimpleDto.builder()
                .id(post.getId())
                .title(post.getTitle())
                .build();
    }

    // Post -> PostDetailDto 변환
    private PostDetailDto convertToDetailDto(Post post) {
        return PostDetailDto.builder()
                .id(post.getId())
                .title(post.getTitle())
                .content(post.getContent())
                .accountName(post.getAccount().getKakaoName())  // 작성자 이름 포함
                .build();
    }
}

