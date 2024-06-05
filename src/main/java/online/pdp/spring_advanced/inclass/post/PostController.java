package online.pdp.spring_advanced.inclass.post;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.net.URL;
import java.util.List;

@RestController
@RequestMapping("/api/posts")
public class PostController {

    private final PostRepository postRepository;

    public PostController(PostRepository postRepository) {
        this.postRepository = postRepository;
    }

    @PostMapping("/populate")
    public ResponseEntity<String> populate() throws IOException {

        URL url = new URL("https://jsonplaceholder.typicode.com/posts");
        ObjectMapper objectMapper = new ObjectMapper();
        List<Post> posts = objectMapper.readValue(url, new TypeReference<>() {});

        postRepository.saveAll(posts);

        return ResponseEntity.ok("Added successfully!");
    }

    @GetMapping
    public ResponseEntity<Page<Post>> getAll(
            @RequestParam(defaultValue = "0", required = false) int page,
            @RequestParam(defaultValue = "0", required = false) int size,
            @RequestParam(defaultValue = "asc", required = false) String direction,
            @RequestParam(defaultValue = "title", required = false) String field
    ) {
        Sort sort = Sort.by(Sort.Direction.fromString(direction), field);
        PageRequest pageRequest = PageRequest.of(page, size, sort);
        return ResponseEntity.ok(postRepository.findAll(pageRequest));
    }

    @GetMapping("/{id}")
    public ResponseEntity<Post> findById(@PathVariable String id) {
        Post post = postRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Post not found with id " + id));

        return ResponseEntity.ok(post);
    }

    @PostMapping
    public ResponseEntity<Post> create(@RequestBody Post post) {
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(postRepository.save(post));
    }



}
