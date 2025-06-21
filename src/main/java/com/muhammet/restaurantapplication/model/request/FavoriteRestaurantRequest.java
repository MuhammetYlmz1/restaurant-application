package com.muhammet.restaurantapplication.model.request;

import lombok.*;

import javax.validation.constraints.NotNull;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class FavoriteRestaurantRequest {
    @NotNull
    private Long userId;
    @NotNull
    private Long restaurantId;
}
