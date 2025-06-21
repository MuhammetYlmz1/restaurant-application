package com.muhammet.restaurantapplication.model.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class OrderFoodDto {
    private FoodDTO food;
    private OrderDto orderDto;
}
