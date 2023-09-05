package com.muhammet.restaurantapplication.service;

import com.muhammet.restaurantapplication.dto.UserDto;
import com.muhammet.restaurantapplication.model.User;

public interface UserService {

    User create(User user);
    UserDto getUser(String username);
    User findUserByUserName(String username);
}
