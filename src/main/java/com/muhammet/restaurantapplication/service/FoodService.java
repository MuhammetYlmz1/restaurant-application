package com.muhammet.restaurantapplication.service;

import com.muhammet.restaurantapplication.dto.FoodDTO;
import com.muhammet.restaurantapplication.dto.requests.CreateFoodRequest;
import com.muhammet.restaurantapplication.dto.requests.UpdateFoodRequest;
import com.muhammet.restaurantapplication.dto.responses.GetAllFoodsResponse;

import java.util.Collection;
import java.util.List;


public interface FoodService {

     List<FoodDTO> getAllFoods();
     void createFood(CreateFoodRequest createFoodRequest);
     void updateFood(UpdateFoodRequest updateFoodRequest);
     void deleteByFoodId(Long id);
     GetAllFoodsResponse findByFoodName(String name);
     FoodDTO getFoodById(Long id);

     List<FoodDTO> getByBranchIdIn(Collection<Long> branchIds);

}


