package com.muhammet.restaurantapplication.repository;

import com.muhammet.restaurantapplication.model.entity.Restaurant;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface RestaurantRepository extends JpaRepository<Restaurant,Long> {

    public Restaurant findByRestaurantName(String name);
    @Query("SELECT CASE WHEN COUNT(r.restaurantName) > 0 THEN true ELSE false END FROM Restaurant r WHERE r.restaurantName = :name")
    boolean existsByRestaurantName(@Param("name") String name);

}
