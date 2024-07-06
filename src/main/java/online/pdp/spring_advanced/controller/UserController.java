package online.pdp.spring_advanced.controller;

import lombok.RequiredArgsConstructor;
import online.pdp.spring_advanced.dto.UserCreateDto;
import online.pdp.spring_advanced.entity.User;
import online.pdp.spring_advanced.mapper.UserMapper;
import online.pdp.spring_advanced.repository.UserRepository;
import online.pdp.spring_advanced.service.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/users")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @PostMapping
    public ResponseEntity<User> create(@RequestBody UserCreateDto dto) {
        return ResponseEntity.status(201).body(userService.create(dto));
    }

}
