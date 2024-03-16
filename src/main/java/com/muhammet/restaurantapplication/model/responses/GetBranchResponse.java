package com.muhammet.restaurantapplication.model.responses;

import com.muhammet.restaurantapplication.model.dto.FoodDTO;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class GetBranchResponse {

    private Long id;

    private String adress;

    private String phone;

    private String district;

    private String restaurantName;

    private List<FoodDTO> menu;

}
