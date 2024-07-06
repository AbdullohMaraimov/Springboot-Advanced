package online.pdp.spring_advanced.service;

import lombok.RequiredArgsConstructor;
import online.pdp.spring_advanced.entity.User;
import online.pdp.spring_advanced.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class OtpServiceImpl implements OtpService{

    private final UserRepository userRepository;

    @Override
    public void generateOtp(User user) {
        user.setOtp(UUID.randomUUID().toString());
        userRepository.save(user);
    }
}
