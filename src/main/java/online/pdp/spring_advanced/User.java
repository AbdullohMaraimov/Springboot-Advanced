package online.pdp.spring_advanced;


import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ToString
public class User {

    private Integer id;
    private String username;
    private String email;
    private String password;

}
