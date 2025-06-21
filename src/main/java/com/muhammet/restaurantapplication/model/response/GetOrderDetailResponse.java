package com.muhammet.restaurantapplication.model.response;

import com.muhammet.restaurantapplication.model.dto.BranchDto;
import com.muhammet.restaurantapplication.model.dto.OrderFoodDto;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
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
    private BigDecimal totalPrice;
    private LocalDateTime orderDate;
    private List<OrderFoodDto> orderFoodDto;
}
