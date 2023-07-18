package com.muhammet.restaurantapplication.dto.responses;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class GetAllFoodsResponse {
    private Long id;
    private String foodName;
    private Double price;
    private Long branchId;

}
