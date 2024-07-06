package online.pdp.spring_advanced.listeners;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import online.pdp.spring_advanced.entity.User;
import online.pdp.spring_advanced.events.UserCreatedEvent;
import online.pdp.spring_advanced.events.UserUpdateEvent;
import online.pdp.spring_advanced.repository.UserRepository;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

import java.time.Instant;

@Slf4j
@Component
@RequiredArgsConstructor
public class UserEventsListener {

    private final UserRepository userRepository;

    @EventListener({UserCreatedEvent.class})
    public void createUserEventListener(UserCreatedEvent event) {
        log.info("--------------User created event is listened -----------------");
        User user = event.getUser();
        user.setCreatedAt(Instant.now());
        userRepository.save(user);
    }

    @EventListener({UserUpdateEvent.class})
    public void updateUserEventListener(UserUpdateEvent event) {
        log.info("--------------User updated event is listened -----------------");
        User user = event.getUser();
        user.setUpdatedAt(Instant.now());
        userRepository.save(user);
    }

}
