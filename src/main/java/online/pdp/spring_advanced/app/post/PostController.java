package online.pdp.spring_advanced.app.post;

import lombok.RequiredArgsConstructor;
import online.pdp.spring_advanced.app.user.Users;
import online.pdp.spring_advanced.app.user.UsersRepository;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.MutationMapping;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.graphql.data.method.annotation.SchemaMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/posts")
public class PostController {

    private final PostRepository postRepository;
    private final UsersRepository usersRepository;

    @GetMapping
    public List<Post> getAll() {
        return postRepository.findAll();
    }


    @GetMapping("/{id}")
    public Post findOne(@PathVariable Integer id) {
        return postRepository.findById(id).orElse(null);
    }


    @SchemaMapping(typeName = "Query", value = "getPost")
    public Post getAll(@Argument Integer id) {
        return postRepository.findById(id)
                .orElse(null);
    }

    @QueryMapping("getPosts") // agar qavs ichidagi nom bilan metod nomi bir xil bo'sa, qavs ichi shart emas
    public List<Post> getPosts() {
        return postRepository.findAll();
    }


    @MutationMapping
    public Post createPost(
            @Argument String title,
            @Argument String body,
            @Argument Integer userId
    ) {
        Users users = usersRepository.findById(userId).get();

        Post post = Post.builder()
                .title(title)
                .body(body)
                .user(users)
                .build();

        return postRepository.save(post);
    }

    @MutationMapping
    public Post updatePost(
            @Argument Integer userId,
            @Argument String title,
            @Argument String body,
            @Argument String rating
    ) {
        Post post = postRepository.findById(userId).get();

        if (title != null) {
            post.setTitle(title);
        }

        if (body != null) {
            post.setBody(body);
        }

        if (rating != null) {
            post.setRating(Rating.findByRate(rating));
        }

        return postRepository.save(post);
    }




}
