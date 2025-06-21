package com.muhammet.restaurantapplication.model.converter;

import com.muhammet.restaurantapplication.mapper.BaseMapper;
import com.muhammet.restaurantapplication.model.entity.Branch;
import com.muhammet.restaurantapplication.model.response.GetBranchResponse;
import org.springframework.stereotype.Component;

@Component
public class GetBranchResponseConverterToBranch implements BaseMapper<GetBranchResponse, Branch> {
    private final FoodDtoConverter converter;

    public GetBranchResponseConverterToBranch(FoodDtoConverter converter) {
        this.converter = converter;
    }

    @Override
    public Branch map(GetBranchResponse getBranchResponse){
        return Branch.builder()
                .id(getBranchResponse.getId())
                .district(getBranchResponse.getDistrict())
                .adress(getBranchResponse.getAdress())
                .phone(getBranchResponse.getPhone())
                .build();
    }

}
