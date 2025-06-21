package com.muhammet.restaurantapplication.model.converter;

import com.muhammet.restaurantapplication.mapper.BaseMapper;
import com.muhammet.restaurantapplication.model.entity.Restaurant;
import com.muhammet.restaurantapplication.model.response.RestaurantResponse;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class RestaurantResponseToRestaurantConverter implements BaseMapper<RestaurantResponse, Restaurant> {
   private final ModelMapper modelMapper;

    @Override
    public Restaurant map(RestaurantResponse response) {
        return modelMapper.map(response, Restaurant.class);
    }
}
