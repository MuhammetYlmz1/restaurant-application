package com.muhammet.restaurantapplication.service;

import com.muhammet.restaurantapplication.model.dto.OrderFoodDto;

import java.util.List;

public interface OrderFoodService {

    List<OrderFoodDto> getOrderFoodByOrderId(Long orderId);
}
