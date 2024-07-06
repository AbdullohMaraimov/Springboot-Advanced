package online.pdp.spring_advanced.service;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import online.pdp.spring_advanced.dto.UserCreateDto;
import online.pdp.spring_advanced.entity.User;
import online.pdp.spring_advanced.events.UserCreatedEvent;
import online.pdp.spring_advanced.events.UserUpdateEvent;
import online.pdp.spring_advanced.mapper.UserMapper;
import online.pdp.spring_advanced.repository.UserRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService{

    private static final Logger log = LoggerFactory.getLogger(UserServiceImpl.class);
    private final UserRepository userRepository;
    private final UserMapper userMapper;
    private final ApplicationEventPublisher publisher;

    @Override
    @Transactional
    public User create(UserCreateDto dto) {
        User user = userMapper.fromCreateDto(dto);
        userRepository.save(user);
        log.info("-------------User created event it published");
        publisher.publishEvent(new UserCreatedEvent(user));
        return user;
    }

    @Override
    public String update(Integer userId, UserCreateDto dto) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("User not found"));

        user.setName(dto.getName());
        user.setAge(dto.getAge());

        userRepository.save(user);
        log.info("-------------User update event it published");
        publisher.publishEvent(new UserUpdateEvent(user));
        return "Updated successfully";
    }

    @Override
    public List<User> getAll() {
        return userRepository.findAll();
    }

    @Override
    public String delete(Integer id) {
        userRepository.deleteById(id);
        return "Deleted successfully";
    }
}
