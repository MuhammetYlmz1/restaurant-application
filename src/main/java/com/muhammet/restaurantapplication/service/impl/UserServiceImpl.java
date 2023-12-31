package com.muhammet.restaurantapplication.service.impl;

import com.muhammet.restaurantapplication.dto.UserDto;
import com.muhammet.restaurantapplication.dto.requests.CreateUserRequest;
import com.muhammet.restaurantapplication.exception.BusinessException.Ex;
import com.muhammet.restaurantapplication.exception.ExceptionUtil;
import com.muhammet.restaurantapplication.model.User;
import com.muhammet.restaurantapplication.repository.UserRepository;
import com.muhammet.restaurantapplication.service.UserService;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    private final ModelMapper modelMapper;
    private final ExceptionUtil exceptionUtil;



    @Override
    public User create(User user) {
        user.setPassword(passwordEncoder.encode(user.getPassword()));

        return userRepository.save(user);

       /* return UserDto.builder().username(save.getUserName())
                .password(save.getPassword())
                .email(save.getEmail())
                .role(save.getRole()).build();*/

    }

    @Override
    public UserDto getUser(String username) {
        var user=findUserByUserName(username);

        return modelMapper.map(user,UserDto.class);

    }


    @Override
    public User findUserByUserName(String username) {
        return userRepository.findByUserName(username)
                .orElseThrow(()->exceptionUtil.buildException(Ex.USER_NOT_FOUND_EXCEPTION));
    }


    private User createUserDtoToUser(CreateUserRequest createUserRequest){
        return User.builder()
                .userName(createUserRequest.getUserName())
                .surname(createUserRequest.getSurname())
                .email(createUserRequest.getEmail())
                .name(createUserRequest.getName())
                .role(createUserRequest.getRole())
                .password(createUserRequest.getPassword())
                .build();

    }

}
