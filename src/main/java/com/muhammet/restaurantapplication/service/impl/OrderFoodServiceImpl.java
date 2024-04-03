package com.muhammet.restaurantapplication.service.impl;

import com.muhammet.restaurantapplication.model.dto.OrderFoodDto;
import com.muhammet.restaurantapplication.model.entity.OrderFood;
import com.muhammet.restaurantapplication.repository.OrderFoodRepository;
import com.muhammet.restaurantapplication.service.OrderFoodService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class OrderFoodServiceImpl implements OrderFoodService {

    private final OrderFoodRepository orderFoodRepository;
    private final ModelMapper modelMapper;

    @Override
    public List<OrderFoodDto> getOrderFoodByOrderId(Long orderId) {

        List<OrderFood> foodList = orderFoodRepository.findByOrderId(orderId);
        List<OrderFoodDto> orderFoodDtos = foodList.stream().map(map -> modelMapper.map(map, OrderFoodDto.class)).toList();

        return orderFoodDtos;
    }
}
