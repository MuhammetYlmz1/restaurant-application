package com.muhammet.restaurantapplication.repository;

import com.muhammet.restaurantapplication.model.entity.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OrderRepository extends JpaRepository<Order, Long> {

}
