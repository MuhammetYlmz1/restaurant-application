package com.muhammet.restaurantapplication.service;

import com.muhammet.restaurantapplication.model.response.TokenResponse;
import com.muhammet.restaurantapplication.model.entity.User;
import com.muhammet.restaurantapplication.model.request.LoginRequest;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public interface AuthService {
    TokenResponse login(LoginRequest loginRequest);
    Optional<User> getAuthenticatedUser();

}
