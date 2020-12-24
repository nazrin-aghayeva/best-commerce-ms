package org.signup.ms;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;

@SpringBootApplication
@EntityScan(basePackages = "org.signup.ms.entities")
public class SignUpApp {
    public static void main(String[] args) {
        SpringApplication.run(SignUpApp.class, args);
    }
}
