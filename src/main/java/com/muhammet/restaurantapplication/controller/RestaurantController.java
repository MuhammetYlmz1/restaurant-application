package com.muhammet.restaurantapplication.controller;

import com.muhammet.restaurantapplication.dto.RestaurantDTO;
import com.muhammet.restaurantapplication.dto.requests.CreateRestaurantRequest;
import com.muhammet.restaurantapplication.dto.requests.UpdateRestaurantRequest;
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

    @GetMapping("/getByRestaurantId/{id}")
    public ResponseEntity<RestaurantDTO> getByRestaurantId(@Valid @PathVariable("id") Long id){
        return ResponseEntity.ok( restaurantService.getRestaurantById(id));
    }
    @GetMapping("/getByRestaurantName/{name}")
    public RestaurantDTO getByRestaurantName(@Valid @PathVariable("name") String name){
        return restaurantService.findByRestaurantName(name);
    }


}
