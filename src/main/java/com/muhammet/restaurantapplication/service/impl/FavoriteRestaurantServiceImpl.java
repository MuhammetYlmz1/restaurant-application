package com.muhammet.restaurantapplication.service.impl;

import com.muhammet.restaurantapplication.exception.ExceptionUtil;
import com.muhammet.restaurantapplication.model.converter.RestaurantEntityToRestaurantResponseConverter;
import com.muhammet.restaurantapplication.model.converter.RestaurantResponseToRestaurantConverter;
import com.muhammet.restaurantapplication.model.converter.UserDtoToUserConverter;
import com.muhammet.restaurantapplication.model.dto.UserDto;
import com.muhammet.restaurantapplication.model.entity.FavoriteRestaurant;
import com.muhammet.restaurantapplication.model.request.FavoriteRestaurantRequest;
import com.muhammet.restaurantapplication.model.response.RestaurantResponse;
import com.muhammet.restaurantapplication.repository.FavoriteRestaurantRepository;
import com.muhammet.restaurantapplication.service.FavoriteRestaurantService;
import com.muhammet.restaurantapplication.service.RestaurantService;
import com.muhammet.restaurantapplication.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class FavoriteRestaurantServiceImpl implements FavoriteRestaurantService {
    private final FavoriteRestaurantRepository favoriteRestaurantRepository;
    private final UserService userService;
    private final RestaurantService restaurantService;
    private final RestaurantResponseToRestaurantConverter responseToRestaurantConverter;
    private final UserDtoToUserConverter userDtoToUserConverter;
    private final RestaurantEntityToRestaurantResponseConverter restaurantEntityToRestaurantResponseConverter;
    private final ExceptionUtil exceptionUtil;

    @Override
    public Boolean addFavoriteRestaurant(FavoriteRestaurantRequest request) {
        var existFavorite = favoriteRestaurantRepository.findByRestaurant_IdAndUser_IdAndRestaurant_DeletedFalse(
                request.getRestaurantId(), request.getUserId());

        UserDto userDto = userService.findByUserId(request.getUserId());
        RestaurantResponse restaurantResponse = restaurantService.getRestaurantById(request.getRestaurantId());
        if (existFavorite.isEmpty()) {
            favoriteRestaurantRepository.save(buildFavoriteRestaurant(userDto, restaurantResponse));
            return Boolean.TRUE;
        }
        return Boolean.FALSE;
    }

    @Override
    public List<RestaurantResponse> getFavoriteRestaurant(Long userId) {
        List<FavoriteRestaurant> existFavorite = favoriteRestaurantRepository.findAllByUser_IdAndRestaurant_DeletedFalse(userId);
        var responseMap = existFavorite
                .stream()
                .map(favoriteRestaurant -> restaurantEntityToRestaurantResponseConverter.map(favoriteRestaurant.getRestaurant()));
        return responseMap.toList();
    }

    private FavoriteRestaurant buildFavoriteRestaurant(UserDto userDto, RestaurantResponse response) {
        return FavoriteRestaurant.builder()
                .restaurant(responseToRestaurantConverter.map(response))
                .user(userDtoToUserConverter.map(userDto))
                .build();
    }

}
