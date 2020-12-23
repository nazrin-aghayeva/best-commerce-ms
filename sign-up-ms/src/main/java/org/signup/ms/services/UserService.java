package org.signup.ms.services;

import org.signup.ms.entities.Users;

import java.util.List;
import java.util.Optional;


public interface UserService {
    Users save(Users user);
    List<Users> findAll();
    void delete(int user_id);
    Optional<Users> findByOne(String email);
    Optional<Users> findById(int user_id);
}
