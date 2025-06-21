package com.muhammet.restaurantapplication.service;

import com.muhammet.restaurantapplication.model.request.CreateOrderRequest;
import com.muhammet.restaurantapplication.model.response.CreateOrderResponse;
import com.muhammet.restaurantapplication.model.response.GetOrderDetailResponse;
import com.muhammet.restaurantapplication.model.response.GetOrderResponse;

import java.util.List;

public interface OrderService {

    CreateOrderResponse create(CreateOrderRequest request);
    List<GetOrderResponse> getAll();

    GetOrderResponse getOrderById(Long id);

    GetOrderDetailResponse getOrderDetaild(Long id);

    Boolean cancelOrder(Long id);

    Boolean completeOrder(Long id);
}
