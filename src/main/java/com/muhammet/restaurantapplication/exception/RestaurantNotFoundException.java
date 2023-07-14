package com.muhammet.restaurantapplication.exception;

public class RestaurantNotFoundException extends RuntimeException{

    public RestaurantNotFoundException(String message) {
        super(message);
    }
}
