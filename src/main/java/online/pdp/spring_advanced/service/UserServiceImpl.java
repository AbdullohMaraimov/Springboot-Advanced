package online.pdp.spring_advanced.service;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import online.pdp.spring_advanced.dto.UserCreateDto;
import online.pdp.spring_advanced.entity.User;
import online.pdp.spring_advanced.events.OtpGenerateEvent;
import online.pdp.spring_advanced.mapper.UserMapper;
import online.pdp.spring_advanced.repository.UserRepository;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService{

    private final UserRepository userRepository;
    private final UserMapper userMapper;
    private final ApplicationEventPublisher publisher;

    @Override
    @Transactional
    public User create(UserCreateDto dto) {
        User user = userMapper.fromCreateDto(dto);
        userRepository.save(user);
        publisher.publishEvent(new OtpGenerateEvent(user)); // event is published
        return user;
    }
}
