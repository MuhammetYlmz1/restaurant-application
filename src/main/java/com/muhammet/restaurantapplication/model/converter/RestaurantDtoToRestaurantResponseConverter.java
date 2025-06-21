package com.muhammet.restaurantapplication.model.converter;

import com.muhammet.restaurantapplication.mapper.BaseMapper;
import com.muhammet.restaurantapplication.model.dto.RestaurantDTO;
import com.muhammet.restaurantapplication.model.response.RestaurantResponse;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class RestaurantDtoToRestaurantResponseConverter implements BaseMapper<RestaurantDTO, RestaurantResponse> {

    private final ModelMapper modelMapper;
    @Override
    public RestaurantResponse map(RestaurantDTO restaurantDTO) {
        return modelMapper.map(restaurantDTO, RestaurantResponse.class);
    }
}
