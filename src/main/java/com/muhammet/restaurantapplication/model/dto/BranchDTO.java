package com.muhammet.restaurantapplication.model.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class BranchDTO {

    private Long id;

    private String adress;

    private String phone;

    private String district;

    private String restaurantName;

    private List<FoodDTO> menu;

}
