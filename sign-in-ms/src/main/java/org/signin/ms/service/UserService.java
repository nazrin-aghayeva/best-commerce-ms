package org.signin.ms.service;

import org.signin.ms.model.User;
import org.signin.ms.repo.UserRepo;
import org.springframework.stereotype.Service;

@Service
public class UserService {
 public UserRepo userRepo;

    public UserService(UserRepo userRepo) {
        this.userRepo = userRepo;
    }

    public User findByEmail(String email){
       return userRepo.findByEmail(email).orElseThrow(RuntimeException::new);
    }
}
