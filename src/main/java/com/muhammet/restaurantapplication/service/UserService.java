package com.muhammet.restaurantapplication.service;

import com.muhammet.restaurantapplication.dto.UserDto;
import com.muhammet.restaurantapplication.dto.requests.CreateUserRequest;
import com.muhammet.restaurantapplication.model.User;

public interface UserService {

    UserDto create(CreateUserRequest user);
    UserDto getUser(String username);
    User findUserByUserName(String username);
}
