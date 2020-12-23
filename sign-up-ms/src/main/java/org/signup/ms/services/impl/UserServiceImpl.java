package org.signup.ms.services.impl;

import org.signup.ms.entities.Users;
import org.signup.ms.payload.request.SignupRequest;
import org.signup.ms.repository.UserRepository;
import org.signup.ms.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

@Service(value = "userService")
public class UserServiceImpl implements UserDetailsService, UserService {
    @Autowired
    UserRepository userRepo;

    @Autowired
    BCryptPasswordEncoder bCryptPasswordEncoder;


    @Override
    public Users save(Users user) {
        return userRepo.save(user);
    }

    @Override
    public List<Users> findAll() {
        List<Users> usersList= new ArrayList<>();
        userRepo.findAll().iterator().forEachRemaining(usersList::add);
        return usersList;
    }

    @Override
    public void delete(int user_id) {
        userRepo.deleteById(user_id);
    }

    @Override
    public Users findByOne(String email) {
        return userRepo.findByEmail(email);
    }

    @Override
    public Users findById(int user_id) {
        Optional<Users> user= userRepo.findById(user_id);
        return user.orElseThrow(RuntimeException::new);
    }

    @Override
    public UserDetails loadUserByUsername(String user_email) throws UsernameNotFoundException {
        Users user= userRepo.findByEmail(user_email);
        if (user== null){
            throw new UsernameNotFoundException("Invalid username or password");
        }
        return new org.springframework.security.core.userdetails.User(user.getEmail(), user.getPassword(), getAuthority());
    }
    private List<SimpleGrantedAuthority> getAuthority() {
        return Arrays.asList(new SimpleGrantedAuthority("ROLE_ADMIN"));
    }
}
