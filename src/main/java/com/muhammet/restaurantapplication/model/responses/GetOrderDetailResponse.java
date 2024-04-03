package com.muhammet.restaurantapplication.model.responses;

import com.muhammet.restaurantapplication.model.dto.BranchDto;
import com.muhammet.restaurantapplication.model.dto.OrderFoodDto;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;

@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class GetOrderDetailResponse {

    private Long id;
    private BranchDto branchDTO;
    private String note;
    private String name;
    private String surname;
    private Integer totalProduct;
    private Double totalPrice;
    private LocalDateTime orderDate;
    private List<OrderFoodDto> orderFoodDto;
}
