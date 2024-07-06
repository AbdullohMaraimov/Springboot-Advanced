package online.pdp.spring_advanced;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class MyController {

    private final Services services;
    private final AuthorRepository authorRepository;

    @PostMapping("/api/add/author")
    public String addAuthor(@RequestBody Author author) {
        return services.addAuthor(author);
    }

    @PostMapping("/api/add/post/{authorId}")
    public String addPost(@RequestBody Post post, @PathVariable Long authorId) {
        Author author = authorRepository.findById(authorId)
                .orElseThrow(() -> new RuntimeException("Author not found"));
        author.getPosts().add(post);
        authorRepository.save(author);

        post.setAuthor(author);
        return services.addPost(post);
    }

}
