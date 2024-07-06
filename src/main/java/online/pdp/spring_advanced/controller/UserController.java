package online.pdp.spring_advanced.controller;

import lombok.RequiredArgsConstructor;
import online.pdp.spring_advanced.dto.UserCreateDto;
import online.pdp.spring_advanced.entity.User;
import online.pdp.spring_advanced.service.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/users")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @PostMapping
    public ResponseEntity<User> create(@RequestBody UserCreateDto dto) {
        return ResponseEntity.status(201).body(userService.create(dto));
    }

    @PutMapping("/{userId}")
    public String update(@PathVariable Integer userId, UserCreateDto dto) {
        return userService.update(userId, dto);
    }

    @GetMapping
    public List<User> getAll() {
        return userService.getAll();
    }

    @DeleteMapping("/{userId}")
    public String delete(@PathVariable Integer userId) {
        return userService.delete(userId);
    }
}
