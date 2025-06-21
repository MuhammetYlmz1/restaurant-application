package com.muhammet.restaurantapplication.controller;

import com.muhammet.restaurantapplication.model.request.FavoriteRestaurantRequest;
import com.muhammet.restaurantapplication.model.response.RestaurantResponse;
import com.muhammet.restaurantapplication.service.FavoriteRestaurantService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/favorite/restaurant")
@RequiredArgsConstructor
public class FavoriteRestaurantController {
    private final FavoriteRestaurantService favoriteRestaurantService;

    @PostMapping("/add")
    public ResponseEntity<Boolean> addFavorite(@RequestBody FavoriteRestaurantRequest request){
        return ResponseEntity.ok(favoriteRestaurantService.addFavoriteRestaurant(request));
    }

    @GetMapping("/{userId}")
    public ResponseEntity<List<RestaurantResponse>> getFavoriteRestaurantByUser(@PathVariable Long userId){
        return ResponseEntity.ok(favoriteRestaurantService.getFavoriteRestaurant(userId));
    }
}
