package com.muhammet.restaurantapplication.model.responses;

import lombok.*;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class CreateOrderResponse {
    private String name;
    private String surname;
    private Double totalPrice;
}
