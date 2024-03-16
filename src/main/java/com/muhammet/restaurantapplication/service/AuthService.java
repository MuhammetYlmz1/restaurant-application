package com.muhammet.restaurantapplication.service;

import com.muhammet.restaurantapplication.model.dto.TokenResponseDto;
import com.muhammet.restaurantapplication.model.entity.User;
import com.muhammet.restaurantapplication.model.requests.LoginRequest;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public interface AuthService {
    TokenResponseDto login(LoginRequest loginRequest);
    Optional<User> getAuthenticatedUser();

}
