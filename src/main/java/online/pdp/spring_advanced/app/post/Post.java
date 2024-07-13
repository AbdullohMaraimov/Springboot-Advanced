package online.pdp.spring_advanced.app.post;

import jakarta.persistence.*;
import lombok.*;
import online.pdp.spring_advanced.app.user.Users;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
public class Post {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String title;
    private String body;
    private Rating rating;
    @ManyToOne(cascade = CascadeType.ALL)
    private Users user;
}
