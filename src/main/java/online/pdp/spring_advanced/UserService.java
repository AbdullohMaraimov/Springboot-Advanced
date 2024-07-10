package online.pdp.spring_advanced;

import lombok.NonNull;

import java.util.Optional;

public class UserService {

    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }


    public User createUser(@NonNull User user) {

        Optional<User> userOptional = userRepository.findByEmail(user.getEmail());

        if (userOptional.isPresent()) {
            throw new RuntimeException("Email already taken: " + user.getEmail());
        }

        userOptional = userRepository.findByUsername(user.getUsername());

        if (userOptional.isPresent()) {
            throw new RuntimeException("Username already exists: " + user.getUsername());
        }

        return userRepository.save(user);
    }

    public User get(Integer id) {
        return userRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("User not found => " + id));
    }

}
