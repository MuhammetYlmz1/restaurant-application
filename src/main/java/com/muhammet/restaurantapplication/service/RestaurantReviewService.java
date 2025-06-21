package com.muhammet.restaurantapplication.service;

import com.muhammet.restaurantapplication.model.request.RestaurantReviewRequest;
import com.muhammet.restaurantapplication.model.response.GetReviewsRestaurantResponse;

import java.util.List;

public interface RestaurantReviewService {
    Boolean createRestaurantReview(RestaurantReviewRequest request);
    List<GetReviewsRestaurantResponse> getReviewsRestaurant(Long restaurantId);
}
