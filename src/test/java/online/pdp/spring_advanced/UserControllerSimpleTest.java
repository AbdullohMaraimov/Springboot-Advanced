package online.pdp.spring_advanced;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class UserControllerSimpleTest {

    UserController userController;

    @Mock
    UserService userService;

    @BeforeEach
    void setUp() {
        new UserController(userService);
    }

    @Test
    void create() {
        User user = User.builder().build();

        when(userService.create(user)).thenReturn(User.builder().id(1).build());

        User actual = userController.create(user);

        assertEquals(1, actual.getId());

        verify(userService, times(1)).create(user);
    }
}