package com.muhammet.restaurantapplication.model.requests;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UpdateBranchRequest {

    private Long id;

    @NotBlank
    private String adress;
    @NotBlank
    private String phone;
    @NotBlank
    private String district;



}
