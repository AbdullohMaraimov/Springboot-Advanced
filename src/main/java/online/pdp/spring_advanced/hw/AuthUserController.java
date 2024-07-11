package online.pdp.spring_advanced.hw;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/authuser")
@RequiredArgsConstructor
public class AuthUserController {

    private final AuthUserService service;


    @PostMapping
    public ResponseEntity<String> create(@Valid @RequestBody AuthUserCreateDTO dto) {
        service.create(dto);
        return new ResponseEntity<>("Successfully Created - User", HttpStatus.CREATED);
    }

    @PutMapping
    public ResponseEntity<String> update(@Valid @RequestBody AuthUserUpdateDTO dto) {
        service.update(dto);
        return new ResponseEntity<>("Successfully Update - User", HttpStatus.OK);

    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> delete(@PathVariable String id) {
        service.delete(id);
        return new ResponseEntity<>("Successfully Deleted - User", HttpStatus.NO_CONTENT);
    }

    @GetMapping("/{id}")
    public ResponseEntity<AuthUserGetDTO> get(@PathVariable String id) {
        return new ResponseEntity<>(service.get(id), HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity<List<AuthUserGetDTO>> list(@Valid AuthUserCriteria criteria) {
        return new ResponseEntity<>(service.list(criteria), HttpStatus.OK);
    }

}
