package com.muhammet.restaurantapplication.dto.requests;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;


@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CreateBranchRequest {


   // private Long id;

    @NotBlank
    private String adress;
    @NotBlank
    private String phone;

    private String district;

    private Long restaurantId;




}
