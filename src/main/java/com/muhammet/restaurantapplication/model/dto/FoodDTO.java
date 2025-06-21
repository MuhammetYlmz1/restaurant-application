package com.muhammet.restaurantapplication.model.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class FoodDTO {

    private Long id;
    private String foodName;
    private BigDecimal price;
    private BranchDto branchDto;
}
