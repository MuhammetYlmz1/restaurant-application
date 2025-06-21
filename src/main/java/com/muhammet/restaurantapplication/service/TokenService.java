package com.muhammet.restaurantapplication.service;

import com.muhammet.restaurantapplication.model.response.ClaimsResponse;

import java.util.Map;

public interface TokenService {

    String token(Map<String, Object> tokenClaims, String subject);

    ClaimsResponse validateToken(String token);
}
