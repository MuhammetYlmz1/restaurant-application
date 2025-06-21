package com.muhammet.restaurantapplication.service;

import com.muhammet.restaurantapplication.model.dto.UserDto;
import com.muhammet.restaurantapplication.model.request.CreateUserRequest;
import com.muhammet.restaurantapplication.model.entity.User;

public interface UserService {

    UserDto create(CreateUserRequest request);
    UserDto getUser(String username);
    User findUserByUserName(String username);
    UserDto findByUserId(Long id);
}
