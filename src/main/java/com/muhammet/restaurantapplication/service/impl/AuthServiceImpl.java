package com.muhammet.restaurantapplication.service.impl;

import com.muhammet.restaurantapplication.dto.TokenResponseDto;
import com.muhammet.restaurantapplication.dto.requests.LoginRequest;
import com.muhammet.restaurantapplication.exception.GenericException;
import com.muhammet.restaurantapplication.service.AuthService;
import com.muhammet.restaurantapplication.service.UserService;
import com.muhammet.restaurantapplication.util.TokenGenerator;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

@Service
public class AuthServiceImpl implements AuthService {

    private final UserService userService;
    private final TokenGenerator tokenGenerator;
    private final AuthenticationManager authenticationManager;

    public AuthServiceImpl(UserService userService, TokenGenerator tokenGenerator, AuthenticationManager authenticationManager) {
        this.userService = userService;
        this.tokenGenerator = tokenGenerator;
        this.authenticationManager = authenticationManager;
    }

    @Override
    public TokenResponseDto login(LoginRequest loginRequest) {
        try {
            Authentication authentication= authenticationManager
                    .authenticate(new UsernamePasswordAuthenticationToken(loginRequest.getUserName(),loginRequest.getPassword()));
            return TokenResponseDto.builder()
                    .accessToken(tokenGenerator.generateToken(authentication))
                    .userDto(userService.getUser(loginRequest.getUserName()))

                    .build();
        }catch (Exception e){
            throw  GenericException.builder()
                    .httpStatus(HttpStatus.NOT_FOUND)
                    .errorMessage("User not found!!")
                    .build();
        }
    }
}
