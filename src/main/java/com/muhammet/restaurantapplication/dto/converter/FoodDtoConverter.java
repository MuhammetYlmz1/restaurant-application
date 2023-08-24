package com.muhammet.restaurantapplication.dto.converter;

import com.muhammet.restaurantapplication.dto.FoodDTO;
import com.muhammet.restaurantapplication.model.Food;
import org.springframework.stereotype.Component;

@Component
public class FoodDtoConverter {

    public FoodDTO convert(Food food){
        return FoodDTO.builder()
                .foodName(food.getFoodName())
                .price(food.getPrice())
                .branchId(food.getBranch().getId())
                .build();
    }

}
