package online.pdp.spring_advanced.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserCreateDto implements Serializable {
    private String email;
    private String username;
    private String password;
}
