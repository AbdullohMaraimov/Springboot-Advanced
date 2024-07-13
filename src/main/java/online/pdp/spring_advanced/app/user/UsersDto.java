package online.pdp.spring_advanced.app.user;

import lombok.Data;
import online.pdp.spring_advanced.app.post.Post;

import java.util.List;

@Data
public class UsersDto {
    private Integer id;
    private String firstName;
    private String lastName;
    private List<Post> posts;

    public UsersDto(Integer id, String firstName, String lastName) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
    }
}
