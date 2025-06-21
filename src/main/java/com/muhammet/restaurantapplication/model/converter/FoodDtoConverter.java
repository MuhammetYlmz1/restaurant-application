package com.muhammet.restaurantapplication.model.converter;

import com.muhammet.restaurantapplication.model.dto.BranchDto;
import com.muhammet.restaurantapplication.model.dto.FoodDTO;
import com.muhammet.restaurantapplication.model.entity.Food;
import org.springframework.stereotype.Component;

@Component
public class FoodDtoConverter {

    public FoodDTO convert(Food food){
        return FoodDTO.builder()
                .foodName(food.getFoodName())
                .price(food.getPrice())
                .branchDto(BranchDto.builder()
                        .phone(food.getBranch().getPhone())
                        .adress(food.getBranch().getAdress())
                        .restaurantName(food.getBranch().getRestaurantId().getRestaurantName())
                        .district(food.getBranch().getDistrict())
                        .build())
                .build();
    }

}
