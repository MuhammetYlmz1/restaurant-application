package com.muhammet.restaurantapplication.model.converter;

import com.muhammet.restaurantapplication.model.dto.RestaurantDTO;
import com.muhammet.restaurantapplication.model.entity.Restaurant;

public class RestaurantDtoConverter {

    public RestaurantDTO convert(Restaurant restaurant){
        return RestaurantDTO.builder()
                .id(restaurant.getId())
                .restaurantName(restaurant.getRestaurantName())
                .phone(restaurant.getPhone())
                .adress(restaurant.getAdress())
                .branchs(null).build();

    }

}
