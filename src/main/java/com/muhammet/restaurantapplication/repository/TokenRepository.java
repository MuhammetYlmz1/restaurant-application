package com.muhammet.restaurantapplication.repository;

public interface TokenRepository {

    void save(String token);
    String getToken(String token);
}
