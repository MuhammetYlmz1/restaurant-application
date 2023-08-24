package com.muhammet.restaurantapplication.service.impl;

import com.muhammet.restaurantapplication.dto.UserDto;
import com.muhammet.restaurantapplication.dto.requests.CreateUserRequest;
import com.muhammet.restaurantapplication.exception.GenericException;
import com.muhammet.restaurantapplication.model.User;
import com.muhammet.restaurantapplication.repository.UserRepository;
import com.muhammet.restaurantapplication.service.UserService;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    private final ModelMapper modelMapper;



    @Override
    public UserDto create(CreateUserRequest createUserRequest) {
        createUserRequest.setPassword(passwordEncoder.encode(createUserRequest.getPassword()));
        User user= createUserDtoToUser(createUserRequest);
        var save=userRepository.save(user);

        return UserDto.builder().username(user.getUserName())
                .email(user.getEmail())
                .role(user.getRole()).build();

    }

    @Override
    public UserDto getUser(String username) {
        var user=findUserByUserName(username);

        return modelMapper.map(user,UserDto.class);

    }


    @Override
    public User findUserByUserName(String username) {
        return userRepository.findByUserName(username)
                .orElseThrow(()->GenericException.builder()
                        .httpStatus(HttpStatus.BAD_REQUEST)
                        .errorMessage("User not found")
                        .build());
    }


    private User createUserDtoToUser(CreateUserRequest createUserRequest){
        return User.builder()
                .userName(createUserRequest.getUserName())
                .surname(createUserRequest.getSurname())
                .email(createUserRequest.getEmail())
                .name(createUserRequest.getName())
                .role(createUserRequest.getRole())
                .build();

    }

}
