package com.muhammet.restaurantapplication.model.dto;

import com.muhammet.restaurantapplication.model.enums.OrderStatus;
import lombok.*;

import java.math.BigDecimal;
import java.util.List;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class OrderDto {

    private Long id;
    private UserDto userDto;
    private BranchDto branchDto;
    private String note;
    private String name;
    private String surname;
    private String userName;
    private String phone;
    private OrderStatus status;
    private Integer totalProduct;
    private BigDecimal totalPrice;
    private List<FoodDTO> foodDTO;
}
