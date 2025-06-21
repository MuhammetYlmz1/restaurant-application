package com.muhammet.restaurantapplication.controller;

import com.muhammet.restaurantapplication.model.request.RestaurantReviewRequest;
import com.muhammet.restaurantapplication.response.ApiResponse;
import com.muhammet.restaurantapplication.service.RestaurantReviewService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/restaurant-review")
public class RestaurantReviewController {

    private final RestaurantReviewService restaurantReviewService;

    public RestaurantReviewController(RestaurantReviewService restaurantReviewService) {
        this.restaurantReviewService = restaurantReviewService;
    }

    @PostMapping()
    public ResponseEntity<ApiResponse<Object>> createRestauantReview(@RequestBody RestaurantReviewRequest request){
        return ApiResponse.builder().data(restaurantReviewService.createRestaurantReview(request)).build();
    }

    @GetMapping("/{restaurantId}")
    public ResponseEntity<ApiResponse<Object>> getReviewsByRestaurant(@PathVariable Long restaurantId){
        return ApiResponse.builder().data(restaurantReviewService.getReviewsRestaurant(restaurantId)).build();
    }

}
