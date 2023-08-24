package com.muhammet.restaurantapplication.exception;

import lombok.Builder;
import org.springframework.http.HttpStatus;

@Builder
public class GenericException extends RuntimeException{
    private String errorMessage;
    private HttpStatus httpStatus;

    public GenericException(String errorMessage,HttpStatus httpStatus) {
        super(errorMessage);
        this.errorMessage = errorMessage;
        this.httpStatus=httpStatus;
    }

    public String getErrorMessage() {
        return errorMessage;
    }
    public HttpStatus httpStatus(){
        return httpStatus;
    }
}
