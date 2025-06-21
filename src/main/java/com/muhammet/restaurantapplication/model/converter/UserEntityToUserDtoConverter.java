package com.muhammet.restaurantapplication.model.converter;

import com.muhammet.restaurantapplication.mapper.BaseMapper;
import com.muhammet.restaurantapplication.model.dto.UserDto;
import com.muhammet.restaurantapplication.model.entity.User;
import org.springframework.stereotype.Component;

@Component
public class UserEntityToUserDtoConverter implements BaseMapper<User, UserDto> {
    @Override
    public UserDto map(User user) {
        return UserDto.builder()
                .id(user.getId())
                .name(user.getName())
                .role(user.getRole())
                .email(user.getEmail())
                .username(user.getUserName())
                .build();
    }
}
