package com.muhammet.restaurantapplication.model.response;

import lombok.*;

import java.math.BigDecimal;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class CreateOrderResponse {
    private String name;
    private String surname;
    private BigDecimal totalPrice;
}
