    package online.pdp.spring_advanced;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;

@Slf4j
@WebMvcTest
class UserControllerWithWebMvcTest {

    @Autowired
    MockMvc mockMvc;

    @MockBean
    UserService userService;

    @Autowired
    ObjectMapper objectMapper;

    @BeforeEach
    void setUp() {
    }

    @Test
    void create() throws Exception {
        User user = User.builder()
                .email("ali@gmail.com")
                .username("ali")
                .password("ali123")
                .otp("123")
                .build();

        mockMvc.perform(MockMvcRequestBuilders.post("/api/users")
                .contentType("application/json")
                .content(objectMapper.writeValueAsString(user)))
                .andExpect(MockMvcResultMatchers.status().isCreated());

        verify(userService, times(1)).create(user);
    }


    @Test
    void createSuccessfulResponse() throws Exception {
        User user = User.builder()
                .email("ali@gmail.com")
                .username("ali")
                .password("ali123")
                .otp("123")
                .build();

        when(userService.create(user)).thenReturn(User.builder().id(1).build());

        MvcResult mvcResult = mockMvc.perform(MockMvcRequestBuilders.post("/api/users")
                        .contentType("application/json")
                        .content(objectMapper.writeValueAsString(user)))
                .andExpect(MockMvcResultMatchers.status().isCreated())
                .andReturn();

        MockHttpServletResponse response = mvcResult.getResponse();
        String contentAsString = response.getContentAsString();
        User returnedUser = objectMapper.readValue(contentAsString, User.class);

        assertEquals(1, returnedUser.getId());

        verify(userService, times(1)).create(user);
    }



        @Test
        void createSuccessfulResponseWithJsonPath() throws Exception {
            User user = User.builder()
                    .email("ali@gmail.com")
                    .username("ali")
                    .password("ali123")
                    .otp("123")
                    .build();

            when(userService.create(user)).thenReturn(User.builder()
                    .id(1)
                    .email("ali@gmail.com")
                    .username("ali")
                    .password("ali123")
                    .otp("123")
                    .build());

            mockMvc.perform(MockMvcRequestBuilders.post("/api/users")
                            .contentType("application/json")
                            .content(objectMapper.writeValueAsString(user)))
                    .andExpect(MockMvcResultMatchers.status().isCreated())
                    .andExpect(jsonPath("$.id").value(1))
                    .andExpect(jsonPath("$.email").value("ali@gmail.com"))
                    .andExpect(jsonPath("$.username").value("ali"))
                    .andExpect(jsonPath("$.otp").value("123"));

            verify(userService, times(1)).create(user);
        }



        @Test
        void createBadRequestResponse() throws Exception {
            User user = User.builder()
                    .password("ali123")
                    .otp("123")
                    .build();

            mockMvc.perform(MockMvcRequestBuilders.post("/api/users")
                            .contentType("application/json")
                            .content(objectMapper.writeValueAsString(user)))
                    .andExpect(MockMvcResultMatchers.status().isBadRequest());

        }


    @Test
    void createBadRequestResponseWithTranslatedExceptions() throws Exception {
        User user = User.builder()
                .password("ali123")
                .otp("123")
                .build();

        MvcResult mvcResult = mockMvc.perform(MockMvcRequestBuilders.post("/api/users")
                        .contentType("application/json")
                        .content(objectMapper.writeValueAsString(user)))
                .andExpect(MockMvcResultMatchers.status().isBadRequest())
                .andReturn();

        String contentAsString = mvcResult.getResponse().getContentAsString();

        AppErrorDto errorDto = objectMapper.readValue(contentAsString, AppErrorDto.class);
        assertEquals("Invalid input", errorDto.getFriendlyMessage());
        assertEquals("/api/users", errorDto.getErrorPath());

    }




    @Test
    void createBadRequestResponseWithJsonPath() throws Exception {
        User user = User.builder()
                .password("ali123")
                .otp("123")
                .build();

        mockMvc.perform(MockMvcRequestBuilders.post("/api/users")
                        .contentType("application/json")
                        .content(objectMapper.writeValueAsString(user)))
                .andExpect(MockMvcResultMatchers.status().isBadRequest())
                .andExpect(jsonPath("$.errorCode").value(400))
                .andExpect(jsonPath("$.errorPath").value("/api/users"))
                .andExpect(jsonPath("$.friendlyMessage").value("Invalid input"))
                .andExpect(jsonPath("$.developerMessage").hasJsonPath())
                .andReturn();

    }
}












