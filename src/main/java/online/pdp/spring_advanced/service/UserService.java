package online.pdp.spring_advanced.service;

import online.pdp.spring_advanced.dto.UserCreateDto;
import online.pdp.spring_advanced.entity.User;


public interface UserService {

    User create(UserCreateDto dto);

}
