package com.b7av3.loginapp;

import com.b7av3.loginapp.service.UserService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.password.PasswordEncoder;

@SpringBootApplication
public class LoginAppApplication {

    public static void main(String[] args) {
        SpringApplication.run(LoginAppApplication.class, args);
    }

    @Bean
    public CommandLineRunner setupDefaultUser(UserService userService, PasswordEncoder passwordEncoder) {
        return args -> {
            // Clear the existing users from the database
            userService.deleteAllUsers();

            // Add default or new users
            //userService.saveUser(new User("user", passwordEncoder.encode("password")));
            // Here, you can add more users as needed or implement logic to dynamically generate or fetch user details

            // Example of adding another user
            // userService.saveUser(new User("admin", passwordEncoder.encode("adminpass"), "ROLE_ADMIN"));
        };
    }

    // Autowire and Beans moved inside the CommandLineRunner to ensure they are used correctly
}
