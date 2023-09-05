package com.muhammet.restaurantapplication.exception;

import com.muhammet.restaurantapplication.model.enums.ResponseConstants;
import com.muhammet.restaurantapplication.response.ApiResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

public abstract class BaseExceptionHandler<T> {

    /**
     * Build error response response entity.
     *
     * @param error the error
     * @param status the status
     * @return the response entity
     */
    protected final ResponseEntity<ApiResponse<Object>> buildErrorResponse(
            T error, HttpStatus status) {
        return ApiResponse.builder(status, ResponseConstants.FAILURE).error(error, status).build();
    }
}
