package com.muhammet.restaurantapplication.config;

import com.muhammet.restaurantapplication.service.UserService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class StartupConfig implements CommandLineRunner {

    private final UserService userService;

    public StartupConfig(UserService userService) {
        this.userService = userService;
    }

    @Override
    public void run(String... args) throws Exception {
        //userService.create(User.builder().email("muhammet@gmail.com").role(UserRole.USER).userName("mydeneme").name("muhammet").surname("yılmaz").password("1234").build());
    }
}
