package com.muhammet.restaurantapplication.controller;

import com.muhammet.restaurantapplication.model.dto.FoodDTO;
import com.muhammet.restaurantapplication.model.requests.CreateFoodRequest;
import com.muhammet.restaurantapplication.model.requests.UpdateFoodRequest;
import com.muhammet.restaurantapplication.service.FoodService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/food")
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
    public void add(@Valid @RequestBody CreateFoodRequest createFoodRequest) throws Exception {
        foodService.createFood(createFoodRequest);
    }
    @PutMapping
    public void update(@Valid @RequestBody UpdateFoodRequest updateFoodRequest){
         foodService.updateFood(updateFoodRequest);
    }

    @GetMapping("/name/{name}")
    public ResponseEntity<FoodDTO> findByFoodName(@Valid @PathVariable String name){
        return ResponseEntity.ok(foodService.findByFoodName(name));
    }

    @GetMapping("/getFoodById/{id}")
    public ResponseEntity<FoodDTO> getById(@PathVariable Long id){
        return ResponseEntity.ok(foodService.getFoodById(id));
    }
    @DeleteMapping
    public void delete(@PathVariable Long id){
        foodService.deleteByFoodId(id);
    }


}
