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
public class RestaurantDTO {


    private Long id;

    private String restaurantName;

    private String phone;
    private String adress;
    private List<GetAllBranchDto> branchs;

}
