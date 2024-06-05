package online.pdp.spring_advanced.inclass.post;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ToString
@Document("posts")
public class Post {

    @Id
    private String postId;
    private String title;
    private String body;
    private Integer userId;
    private Integer id;

}
