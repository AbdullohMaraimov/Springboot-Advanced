package online.pdp.spring_advanced;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.jdbc.Sql;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
class UserRepositoryTest {

    @Autowired
    UserRepository userRepository;

    @BeforeEach
    void setUp() {
    }


    @Test
    void testSaveMethod() {
        User user = User.builder()
                .email("ali@gmail.com")
                .username("ali")
                .password("ali123")
                .otp("123")
                .build();

        User actual = userRepository.save(user);
        assertEquals(1, actual.getId());
    }



    @Test
    @Sql("/sql_file_name.sql")
    void testCountMethod() {
        User user = User.builder()
                .email("ali@gmail.com")
                .username("ali")
                .password("ali123")
                .otp("123")
                .build();

        User actual = userRepository.save(user);
        assertEquals(1, actual.getId());
    }

}