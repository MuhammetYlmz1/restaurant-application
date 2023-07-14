package com.muhammet.restaurantapplication.dto.requests;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

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
