package team.tripbeats.backend.domain.post.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import team.tripbeats.backend.domain.post.dto.CommentCreateDto;
import team.tripbeats.backend.domain.post.dto.CommentDto;
import team.tripbeats.backend.domain.post.service.CommentService;

import java.util.List;

@RestController
@RequestMapping("/comments")
public class CommentController {

    private final CommentService commentService;

    public CommentController(CommentService commentService) {
        this.commentService = commentService;
    }

    // 댓글 생성 (CommentCreateDto 사용)
    @PostMapping
    public CommentDto createComment(@RequestBody CommentCreateDto commentCreateDto) {
        return commentService.createComment(commentCreateDto);
    }

    // 특정 게시글에 대한 댓글 목록 조회
    @GetMapping("/posts")
    public List<CommentDto> getCommentsByPost(@RequestParam Long postId) {
        return commentService.getCommentsByPost(postId);
    }
}