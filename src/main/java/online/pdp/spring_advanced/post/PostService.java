package online.pdp.spring_advanced.post;

import lombok.NonNull;
import online.pdp.spring_advanced.post.dto.CommentCreateDTO;
import online.pdp.spring_advanced.post.dto.PostCreateDto;
import online.pdp.spring_advanced.post.dto.PostDTO;

import java.util.List;

public interface PostService {

    PostDTO getPost(@NonNull Integer id);
    PostDTO createPost(@NonNull PostCreateDto dto);

    Void createComment(@NonNull List<CommentCreateDTO> dtos);
}
