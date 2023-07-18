package com.muhammet.restaurantapplication.controller;

import com.muhammet.restaurantapplication.dto.RestaurantDTO;
import com.muhammet.restaurantapplication.dto.requests.CreateRestaurantRequest;
import com.muhammet.restaurantapplication.dto.requests.UpdateRestaurantRequest;
import com.muhammet.restaurantapplication.service.RestaurantService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/restaurant")
public class RestaurantController {

    private final RestaurantService restaurantService;

    public RestaurantController(RestaurantService restaurantService) {
        this.restaurantService = restaurantService;
    }

    @GetMapping
    public List<RestaurantDTO> getAllRestaurant(){
        return restaurantService.getAllRestaurants();
    }

    @PostMapping
    public void createRestaurant(@Valid @RequestBody CreateRestaurantRequest restaurantRequest) throws Exception {
        restaurantService.createRestaurant(restaurantRequest);
    }
    @GetMapping("/getByRestaurantId/{id}")
    public ResponseEntity<RestaurantDTO> getByRestaurantId(@Valid @PathVariable("id") Long id){
        return ResponseEntity.ok( restaurantService.getRestaurantById(id));
    }
    @PutMapping
    public ResponseEntity<RestaurantDTO> updateRestaurant(@Valid @RequestBody UpdateRestaurantRequest updateRestaurantRequest){
       return ResponseEntity.ok(restaurantService.updateRestaurant(updateRestaurantRequest));
    }
    @GetMapping("/getByRestaurantName/{name}")
    public RestaurantDTO getByRestaurantName(@Valid @PathVariable("name") String name){
        return restaurantService.findByRestaurandName(name);
    }


}
