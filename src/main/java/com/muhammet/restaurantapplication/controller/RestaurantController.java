package com.muhammet.restaurantapplication.controller;

import com.muhammet.restaurantapplication.model.dto.RestaurantDTO;
import com.muhammet.restaurantapplication.model.request.CreateRestaurantRequest;
import com.muhammet.restaurantapplication.model.request.UpdateRestaurantRequest;
import com.muhammet.restaurantapplication.model.response.RestaurantResponse;
import com.muhammet.restaurantapplication.response.ApiResponse;
import com.muhammet.restaurantapplication.service.RestaurantService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/api/restaurant")
public class RestaurantController {

    private final RestaurantService restaurantService;

    public RestaurantController(RestaurantService restaurantService) {
        this.restaurantService = restaurantService;
    }

    @GetMapping
    public ResponseEntity<ApiResponse<Object>> getAllRestaurant(){
        var restaurants =restaurantService.getAllRestaurants();
        return ApiResponse.builder().data(restaurants).build();
    }

    @PostMapping
    public ResponseEntity<ApiResponse<Object>> create(@Valid @RequestBody CreateRestaurantRequest restaurantRequest) throws Exception {
        var create= restaurantService.create(restaurantRequest);
        return ApiResponse.builder().data(create).build();
    }

    @PutMapping
    public ResponseEntity<RestaurantDTO> update(@Valid @RequestBody UpdateRestaurantRequest updateRestaurantRequest){
        return ResponseEntity.ok(restaurantService.update(updateRestaurantRequest));
    }

    @GetMapping("/{id}")
    public ResponseEntity<RestaurantResponse> getByRestaurantId(@Valid @PathVariable Long id){
        return ResponseEntity.ok(restaurantService.getRestaurantById(id));
    }

    @GetMapping("/name")
    public ResponseEntity<RestaurantResponse> getByRestaurantName(@Valid @RequestParam("name") String restaurantName){
        return ResponseEntity.ok(restaurantService.findByRestaurantName(restaurantName));
    }

    @PostMapping("/delete/{id}")
    public ResponseEntity<Boolean> delete(@PathVariable Long id){
        return ResponseEntity.ok(restaurantService.delete(id));
    }
}
