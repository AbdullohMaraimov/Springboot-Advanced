package online.pdp.spring_advanced;

import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.logging.Logger;

public class UserRepository {

    private static final Logger logger = Logger.getLogger(UserRepository.class.getName());

    private static final List<User> users = new ArrayList<>();

    private static final org.slf4j.Logger log = LoggerFactory.getLogger(UserRepository.class);

    private final AtomicInteger generator = new AtomicInteger(3);

    static {
        users.add(new User(1, "ali", "ali@gmail.com", "ali123"));
        users.add(new User(2, "vali", "vali@gmail.com", "vali123"));
    }

    public Optional<User> findById(Integer id) {
        log.info("Repository : getting user by id => " + id);
        return users.stream()
                .filter(user -> user.getId().equals(id))
                .findFirst();
    }

    public User save(User user) {
        if (user == null) {
            throw new RuntimeException("Persisted object can not be null");
        }

        user.setId(generator.getAndIncrement());
        users.add(user);
        log.info("Repository : Getting user by mail: " + user);
        return user;
    }

    public Optional<User> findByEmail(String email) {
        log.info("Repository : Getting user by email: " + email);
        return users.stream()
                .filter(user -> user.getEmail().equals(email))
                .findFirst();
    }

    public Optional<User> findByUsername(String username) {
        log.info("Repository : Getting user by username: " + username);
        return users.stream()
                .filter(user -> user.getUsername().equals(username))
                .findFirst();
    }




}
