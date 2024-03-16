package com.muhammet.restaurantapplication.controller;

import com.muhammet.restaurantapplication.model.requests.CreateOrderRequest;
import com.muhammet.restaurantapplication.model.responses.CreateOrderResponse;
import com.muhammet.restaurantapplication.response.ApiResponse;
import com.muhammet.restaurantapplication.service.OrderService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RequestMapping("/api/order")
@RestController
public class OrderController {

    private final OrderService orderService;

    public OrderController(OrderService orderService) {
        this.orderService = orderService;
    }

    @PostMapping
    public ResponseEntity<ApiResponse<Object>> create(@RequestBody CreateOrderRequest createOrderRequest){
        CreateOrderResponse orderResponse = orderService.create(createOrderRequest);
        return ApiResponse.builder().data(orderResponse).build();
    }

    @GetMapping
    public ResponseEntity<ApiResponse<Object>> getAll(){
        return ApiResponse.builder().data(orderService.getAll()).build();
    }
}
