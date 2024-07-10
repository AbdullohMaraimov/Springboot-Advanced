package online.pdp.spring_advanced;

import lombok.NonNull;

public interface UserService {

    User create(@NonNull User user);

    User get(@NonNull Integer id);

    void delete(Integer id);

}
