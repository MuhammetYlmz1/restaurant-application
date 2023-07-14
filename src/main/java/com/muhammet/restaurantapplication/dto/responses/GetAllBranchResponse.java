package com.muhammet.restaurantapplication.dto.responses;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class GetAllBranchResponse {

    //private Long id;

    private String adress;


    private String phone;


    private String district;

}
