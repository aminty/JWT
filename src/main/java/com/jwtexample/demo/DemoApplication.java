package com.jwtexample.demo;

import com.jwtexample.demo.domain.AppUser;
import com.jwtexample.demo.domain.Role;
import com.jwtexample.demo.service.UserService;
import com.jwtexample.demo.service.impl.UserServiceImpl;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.util.ArrayList;

@SpringBootApplication
public class DemoApplication {

    public static void main(String[] args) {

        SpringApplication.run(DemoApplication.class, args);

    }

    @Bean
    public BCryptPasswordEncoder bCryptPasswordEncoder(){
        return new BCryptPasswordEncoder();
    }

    @Bean
    CommandLineRunner run(UserService userService) {
        return args -> {
            userService.saveRole(new Role(null, "ROLE_USER"));
            userService.saveRole(new Role(null, "ROLE_MANAGER"));
            userService.saveRole(new Role(null, "ROLE_ADMIN"));
            userService.saveRole(new Role(null, "ROLE_SUPER_ADMIN"));

            userService.saveUser(new AppUser(null, "Amin Tavakkoli", "aminty", "1234", new ArrayList<>()));
            userService.saveUser(new AppUser(null, "Ali Tavakkoli", "ality", "1234", new ArrayList<>()));
            userService.saveUser(new AppUser(null, "Hasan Mohammadi", "hmohammadi", "1234", new ArrayList<>()));
            userService.saveUser(new AppUser(null, "Salar Rajabi", "srajabi", "1234", new ArrayList<>()));


            userService.addRoleToAppUser("aminty", "ROLE_SUPER_ADMIN");
            userService.addRoleToAppUser("aminty", "ROLE_ADMIN");
            userService.addRoleToAppUser("aminty", "ROLE_USER");
            userService.addRoleToAppUser("hmohammadi", "ROLE_USER");
            userService.addRoleToAppUser("ality", "ROLE_USER");
            userService.addRoleToAppUser("ality", "ROLE_MANAGER");
            userService.addRoleToAppUser("srajabi", "ROLE_USER");


        };

    }

}
