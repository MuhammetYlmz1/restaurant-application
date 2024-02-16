package com.muhammet.restaurantapplication.model.converter;

import com.muhammet.restaurantapplication.model.dto.BranchDTO;
import com.muhammet.restaurantapplication.model.entity.Branch;
import org.springframework.stereotype.Component;

import java.util.stream.Collectors;

@Component
public class BranchConverter {
    private final FoodDtoConverter converter;

    public BranchConverter(FoodDtoConverter converter) {
        this.converter = converter;
    }

    public BranchDTO convertToBranchDto(Branch branch){
        return BranchDTO.builder()
                .id(branch.getId())
                .district(branch.getDistrict())
                .adress(branch.getAdress())
                .phone(branch.getPhone())
                .restaurantName(branch.getRestaurantId().getRestaurantName())
                .menu(branch.getMenu().stream()
                        .map(converter::convert)
                        .collect(Collectors.toList()))
                .build();
    }

}
