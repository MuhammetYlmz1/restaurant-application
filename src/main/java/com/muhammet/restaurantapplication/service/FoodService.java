package com.muhammet.restaurantapplication.service;

import com.muhammet.restaurantapplication.dto.FoodDTO;
import com.muhammet.restaurantapplication.dto.requests.CreateFoodRequest;
import com.muhammet.restaurantapplication.dto.requests.UpdateFoodRequest;
import com.muhammet.restaurantapplication.dto.responses.GetAllFoodsResponse;
import com.muhammet.restaurantapplication.exception.FoodNotFoundException;
import com.muhammet.restaurantapplication.model.Food;
import com.muhammet.restaurantapplication.repository.BranchRepository;
import com.muhammet.restaurantapplication.repository.FoodRepository;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;


public interface FoodService {

     List<GetAllFoodsResponse> getAllFoods();
     void createFood(CreateFoodRequest createFoodRequest);
     void updateFood(UpdateFoodRequest updateFoodRequest);
     void deleteByFoodId(Long id);
     GetAllFoodsResponse findByFoodName(String name);
     Food getFoodById(Long id);



}


