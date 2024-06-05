package online.pdp.spring_advanced.hw.part1.repository;

import com.mongodb.lang.NonNull;
import online.pdp.spring_advanced.hw.part1.models.User;

import java.util.List;

public interface UserRepository {

    User get(String id);

    List<User> getAll();
    List<User> getAll(int page, int size);

    User save(@NonNull User user);
    List<User> saveAll(@NonNull List<User> posts);

    boolean delete(String id);

    boolean update(String userId, User user);
}
