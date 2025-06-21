package com.muhammet.restaurantapplication.model.converter;

import com.muhammet.restaurantapplication.mapper.BaseMapper;
import com.muhammet.restaurantapplication.model.entity.Restaurant;
import com.muhammet.restaurantapplication.model.response.RestaurantResponse;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class RestaurantEntityToRestaurantResponseConverter implements BaseMapper<Restaurant, RestaurantResponse> {
    private final ModelMapper modelMapper;

    @Override
    public RestaurantResponse map(Restaurant restaurant) {
        return modelMapper.map(restaurant, RestaurantResponse.class);
    }
}
