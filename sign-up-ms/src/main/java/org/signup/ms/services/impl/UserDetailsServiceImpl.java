package org.signup.ms.services.impl;

import org.signup.ms.entities.Users;
import org.signup.ms.repository.UserRepository;
import org.signup.ms.security.UserDetailsImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {
    @Autowired
    UserRepository userRepository;
    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        Users user= userRepository.findByEmail(email).orElseThrow(()-> new UsernameNotFoundException("User with "+ email+ "email couldn't found"));

        return UserDetailsImpl.build(user);
    }
}
