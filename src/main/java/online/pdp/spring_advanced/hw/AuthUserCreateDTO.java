package online.pdp.spring_advanced.hw;

import lombok.Data;

@Data
public class AuthUserCreateDTO {
    private String email;
    private String username;
    private String password;
}
