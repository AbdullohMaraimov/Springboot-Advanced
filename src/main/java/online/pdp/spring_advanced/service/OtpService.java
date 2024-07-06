package online.pdp.spring_advanced.service;

import online.pdp.spring_advanced.entity.User;

public interface OtpService {

    void generateOtp(User user);
}
