package com.muhammet.restaurantapplication.model.converter;

import com.muhammet.restaurantapplication.mapper.BaseMapper;
import com.muhammet.restaurantapplication.model.dto.RestaurantDTO;
import com.muhammet.restaurantapplication.model.entity.Restaurant;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class RestaurantDtoToRestaurantConverter implements BaseMapper<RestaurantDTO, Restaurant> {
   private final ModelMapper modelMapper;

    @Override
    public Restaurant map(RestaurantDTO restaurantDTO) {
        return modelMapper.map(restaurantDTO, Restaurant.class);
    }
}
