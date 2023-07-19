package com.muhammet.restaurantapplication.controller;

import com.muhammet.restaurantapplication.dto.FoodDTO;
import com.muhammet.restaurantapplication.dto.requests.CreateFoodRequest;
import com.muhammet.restaurantapplication.dto.requests.UpdateFoodRequest;
import com.muhammet.restaurantapplication.service.FoodService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/food/")
public class FoodController {

    private final FoodService foodService;

    public FoodController(FoodService foodService) {
        this.foodService = foodService;
    }

    @GetMapping
    public ResponseEntity<List<FoodDTO>> getAllFoods(){
        return ResponseEntity.ok(foodService.getAllFoods());
    }

    @PostMapping
    public void addFood(@Valid @RequestBody CreateFoodRequest createFoodRequest) throws Exception {
        foodService.createFood(createFoodRequest);
    }
    @PutMapping
    public void updateFood(@Valid @RequestBody UpdateFoodRequest updateFoodRequest){
         foodService.updateFood(updateFoodRequest);
    }

    @GetMapping("name/{name}")
    public ResponseEntity<FoodDTO> findByFoodName(@Valid @PathVariable String name){
        return ResponseEntity.ok(foodService.findByFoodName(name));
    }

    @GetMapping("getFoodById/{id}")
    public ResponseEntity<FoodDTO> getById(@PathVariable Long id){
        return ResponseEntity.ok(foodService.getFoodById(id));
    }
    @DeleteMapping
    public void deleteFood(@PathVariable Long id){
        foodService.deleteByFoodId(id);
    }


}
