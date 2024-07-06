package online.pdp.spring_advanced.listeners;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import online.pdp.spring_advanced.entity.User;
import online.pdp.spring_advanced.events.OtpGenerateEvent;
import online.pdp.spring_advanced.repository.UserRepository;
import org.springframework.context.event.EventListener;
import org.springframework.core.annotation.Order;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.event.TransactionPhase;
import org.springframework.transaction.event.TransactionalEventListener;

import java.util.UUID;

@Slf4j
@Component
@RequiredArgsConstructor
public class UserEventsListener {

    private final UserRepository userRepository;

//    @TransactionalEventListener(phase = TransactionPhase.AFTER_COMMIT, condition = "#event.user.email ne null")
//    @Transactional(propagation = Propagation.REQUIRES_NEW)
    @EventListener({OtpGenerateEvent.class})
    @Order(1)
    @Async
    public void generateOtpEventListener(OtpGenerateEvent event) {
        User user = event.getUser();
        user.setOtp(UUID.randomUUID().toString());
        userRepository.save(user);
    }

    @EventListener({OtpGenerateEvent.class})
    @Order(2)
    @Async
    public void verificationMailSenderListener(OtpGenerateEvent event) {

    }

}
