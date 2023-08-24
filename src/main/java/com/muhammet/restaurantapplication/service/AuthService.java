package com.muhammet.restaurantapplication.service;

import com.muhammet.restaurantapplication.dto.TokenResponseDto;
import com.muhammet.restaurantapplication.dto.requests.LoginRequest;
import org.springframework.stereotype.Service;

@Service
public interface AuthService {
    public TokenResponseDto login(LoginRequest loginRequest);
}
