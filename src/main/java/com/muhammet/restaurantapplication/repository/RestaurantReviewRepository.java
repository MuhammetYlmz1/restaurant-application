package com.muhammet.restaurantapplication.repository;

import com.muhammet.restaurantapplication.model.entity.RestaurantReview;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface RestaurantReviewRepository extends JpaRepository<RestaurantReview, Long> {
    Boolean existsByRestaurant_IdAndUser_Id(Long restaurantId, Long userId);
    List<RestaurantReview> findAllByRestaurant_IdAndRestaurant_DeletedFalse(Long restaurantId);
}
