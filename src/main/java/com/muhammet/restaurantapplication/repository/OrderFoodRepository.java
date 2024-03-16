package com.muhammet.restaurantapplication.repository;

import com.muhammet.restaurantapplication.model.entity.OrderFood;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderFoodRepository extends JpaRepository<OrderFood, Long> {
}
