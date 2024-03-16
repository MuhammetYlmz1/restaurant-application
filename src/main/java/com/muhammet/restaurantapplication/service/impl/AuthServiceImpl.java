package com.muhammet.restaurantapplication.service.impl;

import com.muhammet.restaurantapplication.model.dto.TokenResponseDto;
import com.muhammet.restaurantapplication.model.entity.User;
import com.muhammet.restaurantapplication.model.requests.LoginRequest;
import com.muhammet.restaurantapplication.exception.GenericException;
import com.muhammet.restaurantapplication.service.AuthService;
import com.muhammet.restaurantapplication.service.UserService;
import com.muhammet.restaurantapplication.util.TokenGenerator;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class AuthServiceImpl implements AuthService {

    private final UserService userService;
    private final TokenGenerator tokenGenerator;
    private final AuthenticationManager authenticationManager;

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

    public Optional<User> getAuthenticatedUser() {
        try {
            String username = SecurityContextHolder.getContext().getAuthentication().getName();
            return Optional.ofNullable(userService.findUserByUserName(username));
        } catch (Exception e) {
            return Optional.empty();
        }
    }
}
