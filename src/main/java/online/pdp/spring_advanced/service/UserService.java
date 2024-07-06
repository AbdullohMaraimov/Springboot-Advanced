package online.pdp.spring_advanced.service;

import online.pdp.spring_advanced.dto.UserCreateDto;
import online.pdp.spring_advanced.entity.User;

import java.util.List;


public interface UserService {

    User create(UserCreateDto dto);

    String update(Integer userId, UserCreateDto dto);

    List<User> getAll();

    String delete(Integer id);

}
