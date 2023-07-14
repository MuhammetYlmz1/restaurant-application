package com.muhammet.restaurantapplication.dto.requests;

import com.muhammet.restaurantapplication.model.Branch;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CreateRestaurantRequest {


    //private Long id;
    @NotBlank
    private String restaurantName;
    @NotBlank
    private String phone;
    @NotBlank
    private String adress;

    //private List<Branch> branchs;

}
