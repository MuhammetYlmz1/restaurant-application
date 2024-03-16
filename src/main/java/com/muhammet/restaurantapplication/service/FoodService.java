package com.muhammet.restaurantapplication.service;

import com.muhammet.restaurantapplication.model.dto.FoodDTO;
import com.muhammet.restaurantapplication.model.entity.Food;
import com.muhammet.restaurantapplication.model.requests.CreateFoodRequest;
import com.muhammet.restaurantapplication.model.requests.UpdateFoodRequest;

import java.util.Collection;
import java.util.List;


public interface FoodService {

     List<FoodDTO> getAllFoods();
     void createFood(CreateFoodRequest createFoodRequest);
     void updateFood(UpdateFoodRequest updateFoodRequest);
     void deleteByFoodId(Long id);
     FoodDTO findByFoodName(String name);
     FoodDTO getFoodById(Long id);

     List<FoodDTO> getByBranchIdIn(Collection<Long> branchIds);
     List<Food> findAllByIdIn(List<Long> ids);

}


