package com.muhammet.restaurantapplication.controller;

import com.muhammet.restaurantapplication.model.dto.UserDto;
import com.muhammet.restaurantapplication.model.requests.CreateUserRequest;
import com.muhammet.restaurantapplication.service.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/user")
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }
    @PostMapping("/save")
    public ResponseEntity<UserDto> create(@RequestBody CreateUserRequest request){
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(userService.create(request));
    }
    @GetMapping("/{name}")
    public UserDto getUserName(@PathVariable String name){
        return userService.getUser(name);
    }



}
