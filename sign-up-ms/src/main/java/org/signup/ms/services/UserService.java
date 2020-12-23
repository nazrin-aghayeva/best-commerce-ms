package org.signup.ms.services;

import org.signup.ms.entities.Users;
import org.signup.ms.payload.request.SignupRequest;

import java.util.List;


public interface UserService {
    Users save(Users user);
    List<Users> findAll();
    void delete(int user_id);
    Users findByOne(String email);
    Users findById(int user_id);
}
