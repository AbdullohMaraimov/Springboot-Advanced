package online.pdp.spring_advanced.controller;

import lombok.RequiredArgsConstructor;
import online.pdp.spring_advanced.dto.BlogDto;
import online.pdp.spring_advanced.dto.CommentDto;
import online.pdp.spring_advanced.entity.Blog;
import online.pdp.spring_advanced.entity.Comment;
import online.pdp.spring_advanced.service.BlogService;
import online.pdp.spring_advanced.service.CommentService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/comments")
@RequiredArgsConstructor
public class CommentController {

    private final CommentService commentService;

    @PostMapping("/{postId}")
    public String create(@PathVariable Integer postId, @RequestBody CommentDto dto) {
        commentService.create(postId, dto);
        return "Comment created";
    }

    @PutMapping("/{commentId}")
    public String update(@PathVariable Integer commentId, CommentDto dto) {
        return commentService.update(commentId, dto);
    }

    @GetMapping
    public List<Comment> getAll() {
        return commentService.getAll();
    }

    @DeleteMapping("/{commentId}")
    public String delete(@PathVariable Integer commentId) {
        return commentService.delete(commentId);
    }
}
