package com.muhammet.restaurantapplication.dto;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class FoodDTO {

    private Long id;
    private String foodName;
    private Double price;
    private Long branchId;
}
