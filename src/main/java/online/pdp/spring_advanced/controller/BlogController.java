package online.pdp.spring_advanced.controller;

import lombok.RequiredArgsConstructor;
import online.pdp.spring_advanced.dto.BlogDto;
import online.pdp.spring_advanced.entity.Blog;
import online.pdp.spring_advanced.service.BlogService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/blogs")
@RequiredArgsConstructor
public class BlogController {

    private final BlogService blogService;

    @PostMapping
    public ResponseEntity<Blog> create(@RequestBody BlogDto dto) {
        return ResponseEntity.status(201).body(blogService.create(dto));
    }

    @PutMapping("/{blogId}")
    public String update(@PathVariable Integer blogId, BlogDto dto) {
        return blogService.update(blogId, dto);
    }

    @GetMapping
    public List<Blog> getAll() {
        return blogService.getAll();
    }

    @DeleteMapping("/{blogId}")
    public String delete(@PathVariable Integer blogId) {
        return blogService.delete(blogId);
    }
}
