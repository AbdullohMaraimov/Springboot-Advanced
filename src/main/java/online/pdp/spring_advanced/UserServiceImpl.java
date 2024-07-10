package online.pdp.spring_advanced;

import lombok.NonNull;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class UserServiceImpl implements UserService{

    private final UserRepository userRepository;

    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }


    @Override
    public User create(@NonNull User user) {
        log.info(" Saving user: {}", user);
        return userRepository.save(user);
    }

    @Override
    public User get(@NonNull Integer id) {
        log.info(" Get user with id: {}", id);
        return userRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("User not found: " + id));
    }

    @Override
    public void delete(Integer id) {
        userRepository.deleteById(id);
    }
}
