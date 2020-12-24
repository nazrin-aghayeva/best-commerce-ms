package org.signup.ms.config;

import org.signup.ms.entities.ERole;
import org.signup.ms.entities.Roles;
import org.signup.ms.repository.RoleRepository;
import org.signup.ms.repository.UserRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Arrays;

@Configuration
public class AutoRun {
    private final RoleRepository roleRepository;
    private final UserRepository userRepository;

    public AutoRun(RoleRepository roleRepository, UserRepository userRepository) {
        this.roleRepository = roleRepository;
        this.userRepository = userRepository;
    }


    @Bean
    public CommandLineRunner addRoles() {
        return args -> {
            roleRepository.saveAll(Arrays.asList(
                    new Roles(ERole.USER),
                    new Roles(ERole.ADMIN)
            ));
        };

    }
}
