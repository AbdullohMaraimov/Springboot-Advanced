package online.pdp.spring_advanced.hw.part1;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import online.pdp.spring_advanced.hw.part1.models.Address;
import online.pdp.spring_advanced.hw.part1.models.Company;
import online.pdp.spring_advanced.hw.part1.models.Geo;
import online.pdp.spring_advanced.hw.part1.models.User;
import online.pdp.spring_advanced.hw.part1.repository.UserRepository;
import online.pdp.spring_advanced.hw.part1.repository.UserRepositoryImpl;

import java.io.IOException;
import java.net.URL;
import java.util.List;

public class Main {
    public static void main(String[] args) throws IOException {

        UserRepository userRepository = new UserRepositoryImpl();

        // saveManyUsers();  -> saving users

        // userRepository.get("666043732f53481bd01e293b"); // -> get one user by id

        // userRepository.getAll(); -> get all users

        // userRepository.getAll(0, 3); -> get all users based on zipcode 1 or 9 and paginated

        // userRepository.delete("666043732f53481bd01e293b"); -> delete user by id

        // saveOneUser(userRepository);

        // update(userRepository);
    }

    private static void update(UserRepository userRepository) {
        User user = User.builder()
                .id(111)
                .phone("+998974723320")
                .name("Abdulloh")
                .email("abdullo@gmail.com")
                .website("www.iwork.uz")
                .username("idealist")
                .address(new Address("MySt", "Suite", "Tashkent", "121", new Geo("12223", "123")))
                .company(new Company("iwork", "i give you work", "vs"))
                .build();

        userRepository.update("666050bf52ce69797c927686", user);
    }

    private static void saveOneUser(UserRepository userRepository) {
        User user = User.builder()
                .id(111)
                .phone("+998974723322")
                .name("Abdulloh")
                .email("abdullo@gmail.com")
                .website("www.iwork.uz")
                .username("idealist")
                .address(new Address("MySt", "Suite", "Tashkent", "121", new Geo("12223", "123")))
                .company(new Company("iwork", "i give you work", "vs"))
                .build();

        userRepository.save(user);
    }

    private static void saveManyUsers(UserRepository userRepository) throws IOException {

        URL url = new URL("https://jsonplaceholder.typicode.com/users");
        ObjectMapper objectMapper = new ObjectMapper();
        List<User> users = objectMapper.readValue(url, new TypeReference<>() {});

        userRepository.saveAll(users);
    }
}
