package online.pdp.spring_advanced;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class Services {

    private final AuthorRepository authorRepository;
    private final PostRepository postRepository;

    public String addAuthor(Author author) {
        authorRepository.save(author);
        return "Author added successfully";
    }

    public String addPost(Post post) {
        postRepository.save(post);
        return "Post added successfully";
    }

}
