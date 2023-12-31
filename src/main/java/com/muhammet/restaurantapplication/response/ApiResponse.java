package com.muhammet.restaurantapplication.response;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import com.muhammet.restaurantapplication.model.enums.ResponseConstants;
import lombok.Data;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

/**
 * The type Api response.
 *
 * @param <T> the type parameter
 */
@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
        "status",
        "error",
        "code",
        "message",
})
public class ApiResponse<T> {

    @JsonIgnore private HttpHeaders httpHeaders;
    private HttpStatus status;
    private int code;
    private String message;
    private T error;
    private T content;


    private ApiResponse(Builder<T> builder) {
        this.status = builder.status;
        this.httpHeaders = builder.httpHeaders;
        this.code = builder.code;
        this.content = builder.content;
        this.message = builder.message;
        this.error = builder.error;
    }

    /**
     * Builder builder.
     *
     * @param <T> the type parameter
     * @param status the status
     * @param responseConstants the response constants
     * @return the builder
     */
    public static <T> Builder<T> builder(HttpStatus status, ResponseConstants responseConstants) {
        return new Builder<>(status, responseConstants);
    }

    /**
     * Builder builder.
     *
     * @param <T> the type parameter
     * @return the builder
     */
    public static <T> Builder<T> builder() {
        return new Builder<>();
    }

    /**
     * The type Builder.
     *
     * @param <T> the type parameter
     */
    public static class Builder<T> {

        private HttpHeaders httpHeaders = new HttpHeaders();
        private HttpStatus status;
        private int code;
        private T content;
        private T error;
        private String message;

        /**
         * Instantiates a new Builder.
         *
         * @param status the status
         * @param responseConstants the response constants
         */
        public Builder(HttpStatus status, ResponseConstants responseConstants) {
            this.status = status;
            this.code = responseConstants.getCode();
            this.message = responseConstants.getMessage();
        }

        /** Instantiates a new Builder. */
        public Builder() {
            this.status = HttpStatus.OK;
            this.code = ResponseConstants.SUCCESS.getCode();
            this.message = ResponseConstants.SUCCESS.getMessage();
        }

        /**
         * Header builder.
         *
         * @param name the name
         * @param value the value
         * @return the builder
         */
        public Builder<T> header(String name, String value) {
            this.httpHeaders.add(name, value);
            return this;
        }

        /**
         * Data builder.
         *
         * @param object the object
         * @return the builder
         */
        public Builder<T> data(T object) {
            this.content = object;
            return this;
        }

        /**
         * Pageable data builder.
         *
         * @param object the object
         * @return the builder
         */

        /**
         * Error builder.
         *
         * @param obj the obj
         * @return the builder
         */
        public Builder<T> error(T obj) {
            return error(obj, HttpStatus.BAD_REQUEST);
        }

        /**
         * Error builder.
         *
         * @param obj the obj
         * @param status the status
         * @return the builder
         */
        public Builder<T> error(T obj, HttpStatus status) {
            this.code = ResponseConstants.FAILURE.getCode();
            this.status = status;
            this.message = null;
            this.error = obj;
            return this;
        }

        /**
         * Build response entity.
         *
         * @return the response entity
         */
        public ResponseEntity<ApiResponse<T>> build() {
            ApiResponse<T> apiResponse = new ApiResponse<>(this);
            return ResponseEntity.status(status).headers(httpHeaders).body(apiResponse);
        }
    }
}