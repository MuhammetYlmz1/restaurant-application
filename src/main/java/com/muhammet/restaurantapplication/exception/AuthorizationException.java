package com.muhammet.restaurantapplication.exception;

import org.springframework.http.HttpStatus;

public class AuthorizationException extends BaseException{

    public AuthorizationException(String message, String key) {
        super(message, HttpStatus.UNAUTHORIZED);
    }
}
