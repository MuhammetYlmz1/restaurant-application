package com.muhammet.restaurantapplication.service;

import com.muhammet.restaurantapplication.dto.RestaurantDTO;
import com.muhammet.restaurantapplication.dto.requests.CreateRestaurantRequest;
import com.muhammet.restaurantapplication.dto.requests.UpdateRestaurantRequest;
import com.muhammet.restaurantapplication.dto.responses.GetAllBranchResponse;
import com.muhammet.restaurantapplication.exception.RestaurantNotFoundException;
import com.muhammet.restaurantapplication.model.Branch;
import com.muhammet.restaurantapplication.model.Restaurant;
import com.muhammet.restaurantapplication.repository.BranchRepository;
import com.muhammet.restaurantapplication.repository.RestaurantRepository;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;


public interface RestaurantService {

     List<RestaurantDTO> getAllRestaurants();
     void createRestaurant(CreateRestaurantRequest restaurantRequest) throws Exception;
     RestaurantDTO updateRestaurant(UpdateRestaurantRequest updateRestaurantRequest);
     RestaurantDTO getRestaurantById(Long id);
     RestaurantDTO findByRestaurandName(String restaurantName);

}
