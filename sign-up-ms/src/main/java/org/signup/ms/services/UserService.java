package org.signup.ms.services;

import org.signup.ms.entities.Users;
import org.signup.ms.entities.dto.UsersDto;
import java.util.List;


public interface UserService {
    Users save(UsersDto user);
    List<Users> findAll();
    void delete(int user_id);
    Users findByOne(String email);
    Users findById(int user_id);
}
