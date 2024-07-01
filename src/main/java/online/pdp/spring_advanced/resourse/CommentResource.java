package online.pdp.spring_advanced.resourse;

import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import online.pdp.spring_advanced.post.dto.CommentCreateDTO;
import online.pdp.spring_advanced.post.dto.CommentDTO;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.reactive.function.BodyInserter;
import org.springframework.web.reactive.function.BodyInserters;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CommentResource {

    @Value("${comments.base.postComments}")
    private String postComments;

    @Value("${comments.base.saveComments}")
    private String saveComments;

    public List<CommentDTO> getAllComments(@NonNull Integer postId) {
        WebClient webClient = WebClient.create();
        List<CommentDTO> block = webClient.get()
                .uri(postComments, postId)
                .retrieve()
                .bodyToMono(new ParameterizedTypeReference<List<CommentDTO>>() {
                })
                .block();
        return block;
    }

    public void saveAll(List<CommentCreateDTO> commentCreateDTOS) {
        WebClient webClient = WebClient.create();
        Void block = webClient.post()
                .uri(saveComments)
                .body(BodyInserters.fromValue(commentCreateDTOS))
                .retrieve()
                .bodyToMono(Void.class)
                .block();
    }

}
