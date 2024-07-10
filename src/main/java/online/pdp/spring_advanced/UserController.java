package online.pdp.spring_advanced;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/api/users")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public User create(@Valid @RequestBody User user) {
        return userService.create(user);
    }

    @GetMapping("/{id}")
    public User get(@PathVariable Integer id) {
        return userService.get(id);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable Integer id) {
        userService.delete(id);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<AppErrorDto> handleValidationError(MethodArgumentNotValidException e, HttpServletRequest request) {

        Map<String, String> developerMessage = new HashMap<>();
        for (FieldError fieldError : e.getFieldErrors()) {
            developerMessage.put(fieldError.getField(), fieldError.getDefaultMessage());
        }

        AppErrorDto errorDto = new AppErrorDto(
                request.getRequestURI(),
                400,
                "Invalid input",
                developerMessage
        );
        return new ResponseEntity<>(errorDto, HttpStatus.BAD_REQUEST);
    }

}













