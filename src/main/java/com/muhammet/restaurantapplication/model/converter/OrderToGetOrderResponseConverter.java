package com.muhammet.restaurantapplication.model.converter;

import com.muhammet.restaurantapplication.mapper.BaseMapper;
import com.muhammet.restaurantapplication.mapper.BranchMapper;
import com.muhammet.restaurantapplication.model.dto.BranchDto;
import com.muhammet.restaurantapplication.model.entity.Branch;
import com.muhammet.restaurantapplication.model.entity.Order;
import com.muhammet.restaurantapplication.model.response.GetOrderResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class OrderToGetOrderResponseConverter implements BaseMapper<Order, GetOrderResponse> {

    private final BranchMapper branchMapper;

    @Override
    public GetOrderResponse map(Order order) {
        return GetOrderResponse.builder()
                .id(order.getId())
                .branchDTO(convertBranch(order.getBranch()))
                .name(order.getName())
                .surname(order.getSurname())
                .totalPrice(order.getTotalPrice())
                .note(order.getNote())
                .totalProduct(order.getTotalProduct())
                .orderDate(order.getCreatedAt())
                .build();
    }

    private BranchDto convertBranch(Branch branch) {
        return BranchDto.builder()
                .district(branch.getDistrict())
                .phone(branch.getPhone())
                .adress(branch.getAdress())
                .restaurantName(branch.getRestaurantId().getRestaurantName()).build();
    }

}
