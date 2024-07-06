package online.pdp.spring_advanced.events;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import online.pdp.spring_advanced.entity.User;

@Getter
@RequiredArgsConstructor
public final class OtpGenerateEvent{

    private final User user;

}
