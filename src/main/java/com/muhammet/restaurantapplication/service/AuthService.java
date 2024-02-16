package com.muhammet.restaurantapplication.service;

import com.muhammet.restaurantapplication.model.dto.TokenResponseDto;
import com.muhammet.restaurantapplication.model.requests.LoginRequest;
import org.springframework.stereotype.Service;

@Service
public interface AuthService {
    public TokenResponseDto login(LoginRequest loginRequest);
}
