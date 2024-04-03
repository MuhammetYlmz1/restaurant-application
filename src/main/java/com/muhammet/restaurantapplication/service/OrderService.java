package com.muhammet.restaurantapplication.service;

import com.muhammet.restaurantapplication.model.requests.CreateOrderRequest;
import com.muhammet.restaurantapplication.model.responses.CreateOrderResponse;
import com.muhammet.restaurantapplication.model.responses.GetOrderDetailResponse;
import com.muhammet.restaurantapplication.model.responses.GetOrderResponse;

import java.util.List;

public interface OrderService {

    CreateOrderResponse create(CreateOrderRequest request);
    List<GetOrderResponse> getAll();

    GetOrderResponse getOrderById(Long id);

    GetOrderDetailResponse getOrderDetaild(Long id);

    void cancelOrder(Long id);
}
