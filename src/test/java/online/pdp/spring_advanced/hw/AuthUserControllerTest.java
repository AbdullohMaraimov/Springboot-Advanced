package online.pdp.spring_advanced.hw;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(AuthUserController.class)
class AuthUserControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private AuthUserService service;

    @Autowired
    ObjectMapper objectMapper;



    @Test
    void create() throws Exception {
        AuthUserCreateDTO dto = new AuthUserCreateDTO();
        dto.setUsername("ali");
        dto.setEmail("ali@gmail.com");
        dto.setUsername("ali123");

        mockMvc.perform(post("/api/authuser")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(dto)))
                .andExpect(status().isCreated());
    }

    @Test
    void update() throws Exception {
        AuthUserCreateDTO dto = new AuthUserCreateDTO();
        dto.setUsername("ali");
        dto.setEmail("ali@gmail.com");
        dto.setUsername("ali123");

        mockMvc.perform(put("/api/authuser")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(dto)))
                .andExpect(status().isOk())
                .andExpect(content().string("Successfully Update - User"));
    }

    @Test
    void delete() throws Exception {
        doNothing().when(service).delete("1");

        mockMvc.perform(MockMvcRequestBuilders.delete("/api/authuser/1"))
                .andExpect(status().isNoContent())
                .andExpect(content().string("Successfully Deleted - User"));
    }

    @Test
    void get() throws Exception {
        AuthUserGetDTO authUserGetDTO = new AuthUserGetDTO();
        authUserGetDTO.setId(1);
        authUserGetDTO.setName("Ali");

        when(service.get(String.valueOf(1))).thenReturn(authUserGetDTO);

        mockMvc.perform(MockMvcRequestBuilders.get("/api/authuser/1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(1))
                .andExpect(jsonPath("$.name").value("Ali"));
    }

    @Test
    void list() throws Exception {
        List<AuthUserGetDTO> userGetDTOList = new ArrayList<>();
        AuthUserGetDTO authUserGetDTO = new AuthUserGetDTO();
        authUserGetDTO.setId(1);
        authUserGetDTO.setName("Ali");
        userGetDTOList.add(authUserGetDTO);

        AuthUserCriteria criteria = new AuthUserCriteria();

        when(service.list(any(AuthUserCriteria.class))).thenReturn(userGetDTOList);

        mockMvc.perform(MockMvcRequestBuilders.get("/api/authuser")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].id").value("1"))
                .andExpect(jsonPath("$[0].name").value("Ali"));

    }
}