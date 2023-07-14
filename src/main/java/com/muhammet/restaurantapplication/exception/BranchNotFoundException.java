package com.muhammet.restaurantapplication.exception;

public class BranchNotFoundException extends RuntimeException{

    public BranchNotFoundException(String message) {
        super(message);
    }
}
