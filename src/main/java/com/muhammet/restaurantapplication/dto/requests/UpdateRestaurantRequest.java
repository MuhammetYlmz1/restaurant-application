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
public class UpdateRestaurantRequest {

    private Long id;
    @NotBlank
    private String restaurantName;
    @NotBlank
    private String phone;
    @NotBlank
    private String adress;

}
