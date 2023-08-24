package com.muhammet.restaurantapplication.dto.converter;

import com.muhammet.restaurantapplication.dto.RestaurantDTO;
import com.muhammet.restaurantapplication.model.Restaurant;

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
