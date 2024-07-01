package online.pdp.spring_advanced.post.dto;

import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import online.pdp.spring_advanced.post.Post;
import online.pdp.spring_advanced.post.PostRepository;
import online.pdp.spring_advanced.post.PostService;
import online.pdp.spring_advanced.resourse.CommentResource;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;


@RequiredArgsConstructor
@Service
public class PostServiceImpl implements PostService {

    private final PostRepository postRepository;
    private final CommentResource commentResource;

    @Override
    public PostDTO getPost(@NonNull Integer id) {
        Post post = postRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Post not found"));

        return PostDTO.builder()
                .id(post.getId())
                .body(post.getBody())
                .title(post.getTitle())
                .comments(commentResource.getAllComments(id))
                .build();
    }

    @Override
    public PostDTO createPost(@NonNull PostCreateDto dto) {
        Post post = Post.builder()
                .title(dto.getTitle())
                .body(dto.getBody())
                .build();
        postRepository.save(post);
        return PostDTO.builder()
                .id(post.getId())
                .body(post.getBody())
                .title(post.getTitle())
                .build();
    }

    @Override
    public Void createComment(@NonNull List<CommentCreateDTO> dtos) {
        commentResource.saveAll(dtos);
        return null;
    }
}
