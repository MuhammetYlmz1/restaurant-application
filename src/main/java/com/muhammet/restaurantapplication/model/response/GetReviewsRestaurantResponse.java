package com.muhammet.restaurantapplication.model.response;

import lombok.*;

import java.time.LocalDateTime;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class GetReviewsRestaurantResponse {
    private String comment;
    private String restaurantName;
    private LocalDateTime createdAt;
    private int flavourRating;
    private int serviceRating;
}
