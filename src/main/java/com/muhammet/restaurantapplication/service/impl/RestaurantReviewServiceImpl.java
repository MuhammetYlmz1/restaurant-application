package com.muhammet.restaurantapplication.service.impl;

import com.muhammet.restaurantapplication.exception.BusinessException.Ex;
import com.muhammet.restaurantapplication.exception.ExceptionUtil;
import com.muhammet.restaurantapplication.model.entity.Restaurant;
import com.muhammet.restaurantapplication.model.entity.RestaurantReview;
import com.muhammet.restaurantapplication.model.entity.User;
import com.muhammet.restaurantapplication.model.request.RestaurantReviewRequest;
import com.muhammet.restaurantapplication.model.response.GetReviewsRestaurantResponse;
import com.muhammet.restaurantapplication.repository.RestaurantRepository;
import com.muhammet.restaurantapplication.repository.RestaurantReviewRepository;
import com.muhammet.restaurantapplication.repository.UserRepository;
import com.muhammet.restaurantapplication.service.RestaurantReviewService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class RestaurantReviewServiceImpl implements RestaurantReviewService {

    private final RestaurantReviewRepository restaurantReviewRepository;
    private final RestaurantRepository restaurantRepository;
    private final UserRepository userRepository;
    private final ExceptionUtil exceptionUtil;

    @Override
    public Boolean createRestaurantReview(RestaurantReviewRequest request) {
        Restaurant restaurant = restaurantRepository.findById(request.getRestaurantId())
                .orElseThrow(() -> exceptionUtil.buildException(Ex.RESTAURANT_NOT_FOUND_EXCEPTION));

        User user = userRepository.findById(request.getUserId())
                .orElseThrow(() -> exceptionUtil.buildException(Ex.USER_NOT_FOUND_EXCEPTION));

        if (Boolean.TRUE.equals(restaurantReviewRepository.existsByRestaurant_IdAndUser_Id(restaurant.getId(), user.getId()))) {
            throw exceptionUtil.buildException(Ex.RESTAURANT_REVIEW_ALREADY_EXISTS_EXCEPTION);
        }

        restaurantReviewRepository.save(convertRestaurantReviewEntity(request, user, restaurant));
        return Boolean.TRUE;
    }

    @Override
    public List<GetReviewsRestaurantResponse> getReviewsRestaurant(Long restaurantId) {
        return restaurantReviewRepository.findAllByRestaurant_IdAndRestaurant_DeletedFalse(restaurantId)
                .stream().map(this::convertGetReviewsRestaurantResponse).toList();
    }

    private RestaurantReview convertRestaurantReviewEntity(RestaurantReviewRequest request, User user, Restaurant restaurant) {
        return RestaurantReview.builder()
                .comment(request.getComment())
                .flavourRating(request.getFlavourRating())
                .serviceRating(request.getServiceRating())
                .user(user)
                .restaurant(restaurant)
                .build();
    }

    private GetReviewsRestaurantResponse convertGetReviewsRestaurantResponse(RestaurantReview review){
        return GetReviewsRestaurantResponse.builder()
                .comment(review.getComment())
                .restaurantName(review.getRestaurant().getRestaurantName())
                .serviceRating(review.getServiceRating())
                .flavourRating(review.getFlavourRating())
                .createdAt(review.getCreatedAt())
                .build();
    }

}
