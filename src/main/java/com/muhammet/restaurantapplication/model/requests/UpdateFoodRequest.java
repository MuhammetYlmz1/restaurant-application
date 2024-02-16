package com.muhammet.restaurantapplication.model.requests;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UpdateFoodRequest {

    private Long id;
    @NotBlank
    private String foodName;
    @NotNull
    @Min(0)
    private Double price;

}
