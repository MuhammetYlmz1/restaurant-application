package com.muhammet.restaurantapplication.dto.requests;


import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UpdateRestaurantRequest {

    private Long id;
    @NotBlank
    private String restaurantName;
    @NotBlank
    private String phone;
    @NotBlank
    private String adress;

}
