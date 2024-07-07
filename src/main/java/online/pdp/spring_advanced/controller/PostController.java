package online.pdp.spring_advanced.controller;

import lombok.RequiredArgsConstructor;
import online.pdp.spring_advanced.dto.PostCreateDto;
import online.pdp.spring_advanced.dto.PostUpdateDto;
import online.pdp.spring_advanced.entity.Post;
import online.pdp.spring_advanced.service.PostService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/posts")
@RequiredArgsConstructor
public class PostController {

    private final PostService postService;

    @PostMapping
    public ResponseEntity<Post> create(@RequestBody PostCreateDto dto) {
        return ResponseEntity.status(201).body(postService.create(dto));
    }

    @GetMapping("/{id}")
    public Post get(@PathVariable Integer id) throws InterruptedException {
        return postService.findById(id);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Integer id) {
        postService.delete(id);
    }

    @PutMapping
    public String update(@RequestBody PostUpdateDto dto) {
        postService.update(dto);
        return "Post updated";
    }
}
