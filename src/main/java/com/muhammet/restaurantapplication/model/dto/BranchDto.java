package com.muhammet.restaurantapplication.model.dto;

import lombok.*;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class BranchDto {
    private Long id;
    private String adress;

    private String phone;

    private String district;

    private String restaurantName;
}
