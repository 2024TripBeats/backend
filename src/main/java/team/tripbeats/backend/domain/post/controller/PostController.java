package team.tripbeats.backend.domain.post.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import team.tripbeats.backend.domain.post.dto.PostCreateDto;
import team.tripbeats.backend.domain.post.dto.PostDetailDto;
import team.tripbeats.backend.domain.post.dto.PostSimpleDto;
import team.tripbeats.backend.domain.post.service.PostService;

import java.util.List;

@RestController
@RequestMapping("/posts")
@RequiredArgsConstructor
public class PostController {

    private final PostService postService;

    // 게시글 생성
    @PostMapping
    public void createPost(@RequestBody PostCreateDto postCreateDto) {
        postService.createPost(postCreateDto);
    }

    // 게시글 수정
    @PutMapping("/{postId}")
    public void updatePost(@PathVariable Long postId, @RequestBody PostCreateDto postCreateDto) {
        postService.updatePost(postId, postCreateDto);
    }

    @DeleteMapping("/{postId}")
    public void deletePost(@PathVariable Long postId) {
        postService.deletePost(postId);
    }

    // 게시글 전체 목록 간단히 조회 (ID와 제목만)
    @GetMapping("/all")
    public List<PostSimpleDto> getAllSimplePosts() {
        return postService.getAllSimplePosts();
    }

    // 게시글 상세 조회 (ID, 제목, 내용, 작성자 이름)
    @GetMapping("/{postId}")
    public PostDetailDto getPostDetail(@PathVariable Long postId) {
        return postService.getPostDetailById(postId);
    }
}
