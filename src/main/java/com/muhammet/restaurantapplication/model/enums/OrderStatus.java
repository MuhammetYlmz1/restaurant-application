package com.muhammet.restaurantapplication.model.enums;

import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public enum OrderStatus {
    PENDING,
    ACCEPTED,
    CANCELED
}
