package com.muhammet.restaurantapplication.controller;

import com.muhammet.restaurantapplication.model.requests.CreateOrderRequest;
import com.muhammet.restaurantapplication.model.responses.CreateOrderResponse;
import com.muhammet.restaurantapplication.response.ApiResponse;
import com.muhammet.restaurantapplication.service.OrderService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

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

    @GetMapping("/{id}")
    public ResponseEntity<ApiResponse<Object>> getOrderById(@Valid @PathVariable("id") Long id){
        return ApiResponse.builder().data(orderService.getOrderById(id)).build();
    }

    @GetMapping("/detail/{id}")
    public ResponseEntity<ApiResponse<Object>> getOrderDetail(@Valid @PathVariable("id") Long id){
        return ApiResponse.builder().data(orderService.getOrderDetaild(id)).build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponse<Object>> cancelOrder(Long id){
        orderService.cancelOrder(id);
        return ApiResponse.builder().build();
    }
}
