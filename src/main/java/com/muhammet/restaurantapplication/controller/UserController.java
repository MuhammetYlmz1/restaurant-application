package com.muhammet.restaurantapplication.controller;

import com.muhammet.restaurantapplication.dto.UserDto;
import com.muhammet.restaurantapplication.model.User;
import com.muhammet.restaurantapplication.service.UserService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/user")
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }
    @PostMapping
    public User create(User user){
        return userService.create(user);
    }
    @GetMapping("/{name}")
    public UserDto getUserName(@PathVariable String name){
        return userService.getUser(name);
    }



}
