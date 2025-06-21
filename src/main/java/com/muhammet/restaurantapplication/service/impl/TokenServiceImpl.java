package com.muhammet.restaurantapplication.service.impl;

import com.muhammet.restaurantapplication.comp.jwt.component.JwtComponent;
import com.muhammet.restaurantapplication.exception.AuthorizationException;
import com.muhammet.restaurantapplication.model.response.ClaimsResponse;
import com.muhammet.restaurantapplication.service.TokenService;
import io.jsonwebtoken.Claims;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service
@AllArgsConstructor
public class TokenServiceImpl implements TokenService {

    private final JwtComponent jwtComponent;
    private final ModelMapper modelMapper;

    @Override
    public String token(Map<String, Object> tokenClaims, String subject) {
        return jwtComponent.generateAccessToken(tokenClaims, subject);
    }

    @Override
    public ClaimsResponse validateToken(String token) {
        try {
            Claims claims = jwtComponent.verifyToken(token);
            return modelMapper.map(claims, ClaimsResponse.class);
        }catch (Exception e){
            throw new AuthorizationException(e.getMessage(), HttpStatus.UNAUTHORIZED.name());
        }
    }
}
