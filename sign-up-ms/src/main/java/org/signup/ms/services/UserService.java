package org.signup.ms.services;

import org.signup.ms.entities.Users;
import org.signup.ms.repository.UserRepo;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.time.LocalDateTime;
import java.util.Optional;

@Transactional
@Service
public class UserService {

    public UserRepo userRepo;
    private BCryptPasswordEncoder bCryptPasswordEncoder;


    public UserService(UserRepo userRepo, BCryptPasswordEncoder bCryptPasswordEncoder) {
        this.userRepo = userRepo;
        this.bCryptPasswordEncoder= bCryptPasswordEncoder;
    }

    public Optional<Users> findUserByEmail(String user_email){
        return userRepo.findByEmail(user_email);
    }

    public Users saveUser(Users user){
        user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
        user.setConfirm_password(bCryptPasswordEncoder.encode(user.getConfirm_password()));
        user.setActive(1);
        user.setCreated_time(LocalDateTime.now());
        return userRepo.save(user);
    }

}
