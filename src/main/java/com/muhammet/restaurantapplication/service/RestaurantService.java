package com.muhammet.restaurantapplication.service;

import com.muhammet.restaurantapplication.dto.RestaurantDTO;
import com.muhammet.restaurantapplication.dto.requests.CreateRestaurantRequest;
import com.muhammet.restaurantapplication.dto.requests.UpdateRestaurantRequest;

import java.util.List;


public interface RestaurantService {

     List<RestaurantDTO> getAllRestaurants();
     RestaurantDTO create(CreateRestaurantRequest restaurantRequest) throws Exception;
     RestaurantDTO update(UpdateRestaurantRequest updateRestaurantRequest);
     RestaurantDTO getRestaurantById(Long id);
     RestaurantDTO findByRestaurantName(String restaurantName);
     boolean existsByRestaurantName(String name);

}
