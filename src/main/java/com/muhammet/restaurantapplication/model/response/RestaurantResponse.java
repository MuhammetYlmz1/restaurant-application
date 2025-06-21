package com.muhammet.restaurantapplication.model.response;


import lombok.*;

import java.util.List;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class RestaurantResponse {

    private Long id;
    private String restaurantName;
    private String phone;
    private String adress;
    private List<GetAllBranchResponse> branchs;
}
