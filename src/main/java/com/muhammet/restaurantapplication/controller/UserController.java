package com.muhammet.restaurantapplication.controller;

import com.muhammet.restaurantapplication.dto.UserDto;
import com.muhammet.restaurantapplication.dto.requests.CreateUserRequest;
import com.muhammet.restaurantapplication.service.UserService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/user")
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }
    @PostMapping
    public UserDto create(CreateUserRequest user){
        return userService.create(user);
    }



}
