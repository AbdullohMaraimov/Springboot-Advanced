package online.pdp.spring_advanced.post;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import online.pdp.spring_advanced.post.dto.CommentCreateDTO;
import online.pdp.spring_advanced.post.dto.PostCreateDto;
import online.pdp.spring_advanced.post.dto.PostDTO;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("api/v1/posts")
public class PostController {

    private final PostService postService;

    @GetMapping("/{id}")
    public ResponseEntity<PostDTO> getPost(@PathVariable Integer id) {
        return ResponseEntity.ok(postService.getPost(id));
    }


}
