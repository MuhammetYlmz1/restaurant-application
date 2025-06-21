package com.muhammet.restaurantapplication.model.converter;

import com.muhammet.restaurantapplication.mapper.BaseMapper;
import com.muhammet.restaurantapplication.model.dto.UserDto;
import com.muhammet.restaurantapplication.model.entity.User;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class UserDtoToUserConverter implements BaseMapper<UserDto, User> {
    private final ModelMapper modelMapper;

    @Override
    public User map(UserDto userDto) {
        return modelMapper.map(userDto, User.class);
    }
}
