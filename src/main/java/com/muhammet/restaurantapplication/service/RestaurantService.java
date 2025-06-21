package com.muhammet.restaurantapplication.service;

import com.muhammet.restaurantapplication.model.dto.RestaurantDTO;
import com.muhammet.restaurantapplication.model.request.CreateRestaurantRequest;
import com.muhammet.restaurantapplication.model.request.UpdateRestaurantRequest;
import com.muhammet.restaurantapplication.model.response.RestaurantResponse;

import java.util.List;


public interface RestaurantService {

     List<RestaurantDTO> getAllRestaurants();
     RestaurantDTO create(CreateRestaurantRequest restaurantRequest) throws Exception;
     RestaurantDTO update(UpdateRestaurantRequest updateRestaurantRequest);
     RestaurantResponse getRestaurantById(Long id);
     RestaurantResponse findByRestaurantName(String restaurantName);
     boolean existsByRestaurantName(String name);
     Boolean delete(Long id);
}
