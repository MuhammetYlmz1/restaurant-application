package com.muhammet.restaurantapplication.model.dto;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class GetAllBranchDto {

    //private Long id;

    private String adress;


    private String phone;


    private String district;

}
