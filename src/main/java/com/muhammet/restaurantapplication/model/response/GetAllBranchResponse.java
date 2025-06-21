package com.muhammet.restaurantapplication.model.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class GetAllBranchResponse {
    private String adress;
    private String phone;
    private String district;
}
