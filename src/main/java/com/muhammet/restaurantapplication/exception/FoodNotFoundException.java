package com.muhammet.restaurantapplication.exception;

public class FoodNotFoundException extends RuntimeException{

    public FoodNotFoundException(String message) {
        super(message);
    }
}
