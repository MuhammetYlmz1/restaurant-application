package com.muhammet.restaurantapplication.service;

import com.muhammet.restaurantapplication.model.request.FavoriteRestaurantRequest;
import com.muhammet.restaurantapplication.model.response.RestaurantResponse;

import java.util.List;

public interface FavoriteRestaurantService {
    Boolean addFavoriteRestaurant(FavoriteRestaurantRequest request);
    List<RestaurantResponse> getFavoriteRestaurant(Long userId);
}
