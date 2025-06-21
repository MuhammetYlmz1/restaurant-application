package com.muhammet.restaurantapplication.service.impl;

import com.muhammet.restaurantapplication.comp.jwt.constants.TokenClaims;
import com.muhammet.restaurantapplication.comp.jwt.constants.TokenTypes;
import com.muhammet.restaurantapplication.exception.BusinessException.Ex;
import com.muhammet.restaurantapplication.exception.ExceptionUtil;
import com.muhammet.restaurantapplication.model.response.TokenResponse;
import com.muhammet.restaurantapplication.model.dto.UserDto;
import com.muhammet.restaurantapplication.model.entity.User;
import com.muhammet.restaurantapplication.model.request.LoginRequest;
import com.muhammet.restaurantapplication.service.AuthService;
import com.muhammet.restaurantapplication.service.TokenService;
import com.muhammet.restaurantapplication.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class AuthServiceImpl implements AuthService {

    private final UserService userService;
    private final TokenService tokenService;
    private final ExceptionUtil exceptionUtil;

    @Override
    public TokenResponse login(LoginRequest loginRequest) {
        UserDto userDto = userService.getUser(loginRequest.getUserName());

        Map<String, Object> accessTokenClaims = generateAccessTokenClaims(userDto);

        String accessToken = tokenService.token(accessTokenClaims, userDto.getId().toString());
        try {
            return TokenResponse.builder()
                    .accessToken(accessToken)
                    .build();
        }catch (Exception e){
            throw  exceptionUtil.buildException(Ex.WRONG_CREDENTIALS_EXCEPTION);
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

    private Map<String, Object> generateAccessTokenClaims(UserDto userDto){
        Map<String, Object> claims = new HashMap<>();

        claims.put(TokenClaims.NAME.getValue(), userDto.getName());
        claims.put(TokenClaims.EMAIL.getValue(), userDto.getEmail());
        claims.put(TokenClaims.TYPE.getValue(), TokenTypes.BEARER);
        claims.put(TokenClaims.USERNAME.getValue(), userDto.getUsername());

        return claims;
    }
}
