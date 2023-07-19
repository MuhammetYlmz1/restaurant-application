package com.muhammet.restaurantapplication.service;

import com.muhammet.restaurantapplication.dto.RestaurantDTO;
import com.muhammet.restaurantapplication.dto.requests.CreateRestaurantRequest;
import com.muhammet.restaurantapplication.dto.requests.UpdateRestaurantRequest;

import java.util.List;


public interface RestaurantService {

     List<RestaurantDTO> getAllRestaurants();
     void createRestaurant(CreateRestaurantRequest restaurantRequest) throws Exception;
     RestaurantDTO updateRestaurant(UpdateRestaurantRequest updateRestaurantRequest);
     RestaurantDTO getRestaurantById(Long id);
     RestaurantDTO findByRestaurandName(String restaurantName);

}
