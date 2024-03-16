package com.muhammet.restaurantapplication.model.dto;

import lombok.*;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class BranchDto {

    private String adress;

    private String phone;

    private String district;

    private String restaurantName;
}
