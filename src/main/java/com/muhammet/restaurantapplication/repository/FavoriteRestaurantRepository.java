package com.muhammet.restaurantapplication.repository;

import com.muhammet.restaurantapplication.model.entity.FavoriteRestaurant;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface FavoriteRestaurantRepository extends JpaRepository<FavoriteRestaurant, Long> {

    Optional<FavoriteRestaurant> findByRestaurant_IdAndUser_IdAndRestaurant_DeletedFalse(Long userId, Long restaurantId);
    List<FavoriteRestaurant> findAllByUser_IdAndRestaurant_DeletedFalse(Long userId);
}
