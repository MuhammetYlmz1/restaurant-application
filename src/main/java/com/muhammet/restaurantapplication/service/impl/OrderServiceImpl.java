package com.muhammet.restaurantapplication.service.impl;

import com.muhammet.restaurantapplication.exception.BusinessException.Ex;
import com.muhammet.restaurantapplication.exception.ExceptionUtil;
import com.muhammet.restaurantapplication.model.converter.GetBranchResponseConverterToBranch;
import com.muhammet.restaurantapplication.model.converter.OrderToGetOrderResponseConverter;
import com.muhammet.restaurantapplication.model.dto.OrderFoodDto;
import com.muhammet.restaurantapplication.model.entity.*;
import com.muhammet.restaurantapplication.model.requests.CreateOrderRequest;
import com.muhammet.restaurantapplication.model.responses.CreateOrderResponse;
import com.muhammet.restaurantapplication.model.responses.GetBranchResponse;
import com.muhammet.restaurantapplication.model.responses.GetOrderDetailResponse;
import com.muhammet.restaurantapplication.model.responses.GetOrderResponse;
import com.muhammet.restaurantapplication.repository.OrderRepository;
import com.muhammet.restaurantapplication.service.*;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.jetbrains.annotations.NotNull;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@Service
@Slf4j
public class OrderServiceImpl implements OrderService {

    private final OrderFoodService orderFoodService;
    private final OrderRepository orderRepository;
    private final UserService userService;
    private final BranchService branchService;
    private final FoodService foodService;
    private final ModelMapper modelMapper;
    private final ExceptionUtil exceptionUtil;
    private final GetBranchResponseConverterToBranch getBranchResponseConverterToBranch;
    private final OrderToGetOrderResponseConverter orderToGetOrderResponseConverter;

    @Override
    public List<GetOrderResponse> getAll() {
        List<Order> orders = orderRepository.findAll();

        List<GetOrderResponse> getOrderResponseList = orders
                .stream()
                .map(orderToGetOrderResponseConverter::map).toList();

        return getOrderResponseList;
    }

    @Override
    public GetOrderResponse getOrderById(Long id) {
        Optional<Order> order = orderRepository.findById(id);
        if (order.isEmpty()){
            throw exceptionUtil.buildException(Ex.ORDER_NOT_FOUND);
        }

        GetOrderResponse getOrderResponse = orderToGetOrderResponseConverter.map(order.get());

        return getOrderResponse;
    }

    @Override
    public GetOrderDetailResponse getOrderDetaild(Long id) {

        Optional<Order> order = orderRepository.findById(id);
        if (order.isEmpty()){
            throw exceptionUtil.buildException(Ex.ORDER_NOT_FOUND);
        }

        GetOrderResponse getOrderResponse = orderToGetOrderResponseConverter.map(order.get());
        List<OrderFoodDto> orderFoodDtos = orderFoodService.getOrderFoodByOrderId(order.get().getId());

        GetOrderDetailResponse getOrderDetailResponse = GetOrderDetailResponse.builder()
                .id(getOrderResponse.getId())
                .branchDTO(getOrderResponse.getBranchDTO())
                .orderFoodDto(orderFoodDtos)
                .orderDate(getOrderResponse.getOrderDate())
                .totalPrice(getOrderResponse.getTotalPrice())
                .note(getOrderResponse.getNote())
                .name(getOrderResponse.getName())
                .surname(getOrderResponse.getSurname())
                .totalProduct(getOrderResponse.getTotalProduct())
                .build();

        return getOrderDetailResponse;
    }

    @Override
    public void cancelOrder(Long id) {
        Optional<Order> order = orderRepository.findById(id);
        if (order.isEmpty()){
            throw exceptionUtil.buildException(Ex.ORDER_NOT_FOUND);
        }
        orderRepository.delete(order.get());
    }

    @Override
    public CreateOrderResponse create(CreateOrderRequest request) {
        User user = userService.findByUserId(request.getUserId());
        GetBranchResponse getBranchResponse = branchService.getById(request.getBranchId());
        Branch branch = modelMapper.map(getBranchResponse, Branch.class);

        Order order = requestToOrder(request,user,branch);

        Order orderFoods = getOrderFoods(request, order);

        Order savedOrder = orderRepository.save(orderFoods);

        return convertToResponse(savedOrder);
    }

    @NotNull
    private Order getOrderFoods(CreateOrderRequest request, Order order) {
        List<OrderFood> orderFoods = new ArrayList<>();
        for (Long foodId : request.getFoods()) {
            fillOrderFood(order, foodId, orderFoods);
        }

        order.setOrderFoods(orderFoods);
        order.setTotalProduct(orderFoods.size());
        order.setTotalPrice(orderFoods.stream().mapToDouble(f -> f.getFood().getPrice()).sum());
        return order;
    }

    private void fillOrderFood(Order order, Long foodId, List<OrderFood> orderFoods) {
        Food food = foodService.findById(foodId);
        OrderFood orderFood = new OrderFood();
        orderFood.setOrder(order);
        orderFood.setFood(food);
        orderFoods.add(orderFood);
    }

    private Order requestToOrder(CreateOrderRequest createOrderRequest, User user, Branch branch){
        return Order.builder()
                .user(user)
                .name(createOrderRequest.getName())
                .surname(createOrderRequest.getSurname())
                .phone(createOrderRequest.getPhone())
                .branch(branch)
                .note(createOrderRequest.getNote())
                .build();
    }

    private CreateOrderResponse convertToResponse(Order order){
        return CreateOrderResponse.builder()
                .name(order.getName())
                .surname(order.getSurname())
                .totalPrice(order.getTotalPrice())
                .build();
    }
}
