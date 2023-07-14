package com.muhammet.restaurantapplication.repository;

import com.muhammet.restaurantapplication.model.Restaurant;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RestaurantRepository extends JpaRepository<Restaurant,Long> {

    public Restaurant findByRestaurantName(String name);

}
