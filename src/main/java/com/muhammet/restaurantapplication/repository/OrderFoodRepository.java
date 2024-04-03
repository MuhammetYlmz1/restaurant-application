package com.muhammet.restaurantapplication.repository;

import com.muhammet.restaurantapplication.model.entity.OrderFood;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OrderFoodRepository extends JpaRepository<OrderFood, Long> {

    List<OrderFood> findByOrderId(Long orderId);

}
