package com.muhammet.restaurantapplication.service.impl;

import com.muhammet.restaurantapplication.model.converter.OrderToGetOrderResponseConverter;
import com.muhammet.restaurantapplication.model.entity.*;
import com.muhammet.restaurantapplication.model.requests.CreateOrderRequest;
import com.muhammet.restaurantapplication.model.responses.CreateOrderResponse;
import com.muhammet.restaurantapplication.model.responses.GetOrderResponse;
import com.muhammet.restaurantapplication.repository.OrderFoodRepository;
import com.muhammet.restaurantapplication.repository.OrderRepository;
import com.muhammet.restaurantapplication.service.BranchService;
import com.muhammet.restaurantapplication.service.FoodService;
import com.muhammet.restaurantapplication.service.OrderService;
import com.muhammet.restaurantapplication.service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor
@Service
@Slf4j
public class OrderServiceImpl implements OrderService {

    private final OrderRepository orderRepository;
    private final UserService userService;
    private final BranchService branchService;
    private final FoodService foodService;
    private final OrderFoodRepository orderFoodRepository;
    private final ModelMapper modelMapper;
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
    public CreateOrderResponse create(CreateOrderRequest createOrderRequest) {

        User user = userService.findByUserId(createOrderRequest.getUserId());

       /* if (user.getId().equals(authService.getAuthenticatedUser().get().getId())){
            throw exceptionUtil.buildException(Ex.FORBIDDEN_EXCEPTION);
        }*/

        Branch branch = branchService.getById(createOrderRequest.getBranchId());
        Order order = requestToOrder(createOrderRequest, user, branch);
        orderRepository.save(order);

        //saveOrderFood(order, createOrderRequest);

        return convertToResponse(createOrderRequest);
    }

    private Order requestToOrder(CreateOrderRequest createOrderRequest, User user, Branch branch){
        List<Food> foods = foodService.findAllByIdIn(createOrderRequest.getFoods());

        return Order.builder()
                .user(user)
                .name(createOrderRequest.getName())
                .surname(createOrderRequest.getSurname())
                .phone(createOrderRequest.getPhone())
                .branch(branch)
                .note(createOrderRequest.getNote())
                .foods(foods)
                .totalProduct(foods.size())
                .totalPrice(priceCalculate(createOrderRequest.getFoods()))
                .build();
    }

    private CreateOrderResponse convertToResponse(CreateOrderRequest request){
        return CreateOrderResponse.builder()
                .name(request.getName())
                .surname(request.getSurname())
                .totalPrice(priceCalculate(request.getFoods()))
                .build();
    }

    private void saveOrderFood(Order order, CreateOrderRequest request){
        OrderFood orderFood = new OrderFood();
        orderFood.setOrder(order);
        List<Food> foodList = getFoods(request.getFoods());

        foodList.forEach(food -> {
            orderFood.setFood(food);
            orderFood.setCreatedAt(order.getCreatedAt());
            orderFoodRepository.save(orderFood);
        });
    }

    private Double priceCalculate(List<Long> foodsIds){
        Double totalPrice = 0.0;
        List<Food> foods = getFoods(foodsIds);
        for (Double price : foods.stream().map(Food::getPrice).toList()){
            totalPrice += price;
        }
        return totalPrice;
    }

    private List<Food> getFoods(List<Long> ids){
        return foodService.findAllByIdIn(ids);
    }

}
