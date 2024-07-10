package online.pdp.spring_advanced;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class UserServiceTest {

    UserService userService;

    @Mock
    UserRepository userRepository;

    @BeforeEach
    void setUp() {
        // userRepository = mock(UserRepository.class); if we use annotation @Mock for repo, we dont need to do this line
        userService = new UserService(userRepository);
    }

    @Test
    void createUser() {
        User user = User.builder()
                .email("user@gmail.com")
                .username("user")
                .password("user123")
                .build();

        when(userRepository.save(user)).thenReturn( // setting expectations
                User.builder()
                        .id(3)
                        .username(user.getUsername())
                        .password(user.getPassword())
                        .email(user.getEmail())
                        .build());

        User actualUser = userService.createUser(user);

        assertEquals(3, actualUser.getId());
        assertEquals("user", actualUser.getUsername());
        assertEquals("user@gmail.com", actualUser.getEmail());

        verify(userRepository, atLeast(1)).save(user);
    }

    @Test
    void canNotBeNull() {
        User user = null;

        assertThrows(RuntimeException.class, () -> userService.createUser(null));
    }

    @Test
    void emailAlreadyTaken() {
        User user = User.builder()
                .email("ali@gmail.com")
                .username("ali")
                .password("ali123")
                .build();

        when(userRepository.findByEmail(anyString())).thenReturn(Optional.of(new User()));

        assertThrows(RuntimeException.class, () -> userService.createUser(user));

        verify(userRepository, times(1)).findByEmail(anyString());
    }

    @Test
    void usernameAlreadyTaken() {
        User user = User.builder()
                .email("aliali@gmail.com")
                .username("ali")
                .build();

        when(userRepository.findByEmail(anyString())).thenReturn(Optional.empty());
        when(userRepository.findByUsername(anyString())).thenReturn(Optional.of(new User()));

        assertThrows(RuntimeException.class, () -> userService.createUser(user));

        verify(userRepository, times(1)).findByEmail(anyString());
        verify(userRepository, times(1)).findByUsername(anyString());
    }

    @Test
    void successfullySavedTest() {
        User user = User.builder()
                .email("iman@gmail.com")
                .username("iman")
                .build();

        when(userRepository.findByEmail(anyString())).thenReturn(Optional.empty());
        when(userRepository.findByUsername(anyString())).thenReturn(Optional.empty());
        when(userRepository.save(user)).thenReturn(User.builder()
                .id(10)
                .email("iman@gmail.com")
                .username("iman")
                .build());

        User actual = userService.createUser(user);

        assertEquals(10, actual.getId());
        assertEquals("iman", actual.getUsername());
        assertEquals("iman@gmail.com", actual.getEmail());

        verify(userRepository, times(1)).findByEmail(anyString());
        verify(userRepository, times(1)).findByUsername(anyString());
        verify(userRepository, times(1)).save(user);
    }

    @Test
    void get() {

        User user = new User(1, "ali", "ali@gmail.com", "ali123");
        when(userRepository.findById(1)).thenReturn(Optional.of(user));

        User actual = userService.get(1);

        assertEquals(1, actual.getId());
        assertEquals(user.getEmail(), actual.getEmail());
        assertEquals(user.getUsername(), actual.getUsername());

        verify(userRepository, times(1)).findById(1);

    }

    @Test
    void failedGetTest() {
        when(userRepository.findById(anyInt())).thenReturn(Optional.empty());
        assertThrows(RuntimeException.class, () -> userService.get(anyInt()));
        verify(userRepository, times(1)).findById(anyInt());
    }
}