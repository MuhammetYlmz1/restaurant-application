package com.muhammet.restaurantapplication.repository;

import com.muhammet.restaurantapplication.model.Food;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface FoodRepository extends JpaRepository<Food,Long> {


     Food findByFoodName(String foodName);

}
