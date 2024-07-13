package online.pdp.spring_advanced.app.user;

import lombok.RequiredArgsConstructor;
import online.pdp.spring_advanced.app.post.PostRepository;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Objects;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/users")
class UsersController {

    private final UsersRepository userRepository;
    private final PostRepository postRepository;


    @GetMapping
    public List<UsersDto> getAll() {
        List<Users> users = userRepository.findAll();
        return users.stream().map(user -> {
             var dto = new UsersDto(user.getId(), user.getFirstName(), user.getLastName());
             dto.setPosts(postRepository.findByUser_Id(user.getId()));
             return dto;
        }).toList();
    }

    @PutMapping
    public Users update(@RequestBody UserUpdateDTO dto) {
        Users user = userRepository.findById(dto.getId())
                .orElseThrow(() -> new RuntimeException("Not  found"));
        if (Objects.nonNull(dto.getFirstName()))
            user.setFirstName(dto.getFirstName());
        if (Objects.nonNull(dto.getLastName()))
            user.setLastName(dto.getLastName());
        userRepository.save(user);
        return user;
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable Integer id) {
        userRepository.deleteById(id);
    }


}
