package online.pdp.spring_advanced.hw;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class AuthUserService {

    public void create(AuthUserCreateDTO dto) {

    }


    public void update(AuthUserUpdateDTO dto) {

    }

    public void delete(String id) {

    }

    public AuthUserGetDTO get(String id) {
        return null;
    }

    public List<AuthUserGetDTO> list(AuthUserCriteria authUserCriteria) {
        return null;
    }

}
