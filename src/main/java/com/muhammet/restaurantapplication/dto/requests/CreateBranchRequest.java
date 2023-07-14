package com.muhammet.restaurantapplication.dto.requests;

import com.muhammet.restaurantapplication.model.Food;
import com.muhammet.restaurantapplication.model.Restaurant;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;


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
