package com.muhammet.restaurantapplication.comp.jwt.constants;

public enum TokenClaims {

    TYPE("type"),
    SUBJECT("sub"),
    NAME("name"),
    USERNAME("username"),
    EMAIL("email");

    private final String value;

    TokenClaims(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }

}
