package com.muhammet.restaurantapplication.model.request;

import lombok.*;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class RestaurantReviewRequest {
    @NotNull
    private Long restaurantId;
    @NotNull
    private Long userId;
    @Min(0)
    @Max(5)
    private int flavourRating;
    @Min(0)
    @Max(5)
    private int serviceRating;
    @NotBlank
    private String comment;
}
