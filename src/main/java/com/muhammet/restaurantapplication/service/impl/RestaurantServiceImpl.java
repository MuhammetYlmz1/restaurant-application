package com.muhammet.restaurantapplication.service.impl;

import com.muhammet.restaurantapplication.exception.BusinessException.Ex;
import com.muhammet.restaurantapplication.exception.ExceptionUtil;
import com.muhammet.restaurantapplication.model.dto.GetAllBranchDto;
import com.muhammet.restaurantapplication.model.dto.RestaurantDTO;
import com.muhammet.restaurantapplication.model.entity.Branch;
import com.muhammet.restaurantapplication.model.entity.Restaurant;
import com.muhammet.restaurantapplication.model.request.CreateRestaurantRequest;
import com.muhammet.restaurantapplication.model.request.UpdateRestaurantRequest;
import com.muhammet.restaurantapplication.model.response.RestaurantResponse;
import com.muhammet.restaurantapplication.repository.RestaurantRepository;
import com.muhammet.restaurantapplication.service.RestaurantService;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Service
public class RestaurantServiceImpl implements RestaurantService {

    private final RestaurantRepository restaurantRepository;
    private final ModelMapper modelMapper;
    private final ExceptionUtil exceptionUtil;

    public RestaurantServiceImpl(RestaurantRepository restaurantRepository, ModelMapper modelMapper, ExceptionUtil exceptionUtil) {

        this.restaurantRepository = restaurantRepository;
        this.modelMapper = modelMapper;
        this.exceptionUtil = exceptionUtil;
    }

    public List<RestaurantDTO> getAllRestaurants() {
        return restaurantRepository.findAll().stream()
                .map(restaurants -> this.modelMapper.map(restaurants, RestaurantDTO.class)).collect(Collectors.toList());
    }

    public RestaurantDTO create(CreateRestaurantRequest restaurantRequest) {
        boolean existName = existsByRestaurantName(restaurantRequest.getRestaurantName());
        if (existName) {
            throw exceptionUtil.buildException(Ex.RESTAURANT_ALREADY_EXISTS_EXCEPTION);
        }
        Restaurant restaurant = modelMapper.map(restaurantRequest, Restaurant.class);
        restaurantRepository.save(restaurant);

        return modelMapper.map(restaurant, RestaurantDTO.class);
    }

    public RestaurantDTO update(UpdateRestaurantRequest updateRestaurantRequest) {
        Restaurant updating = this.restaurantRepository.findById(updateRestaurantRequest.getId())
                .orElseThrow(() -> exceptionUtil.buildException(Ex.RESTAURANT_NOT_FOUND_EXCEPTION));

        updating.setPhone(updateRestaurantRequest.getPhone());
        updating.setAdress(updateRestaurantRequest.getAdress());
        updating.setRestaurantName(updateRestaurantRequest.getRestaurantName());

        Restaurant restaurant = restaurantRepository.save(updating);
        return convertToRestaurantDto(restaurant);

    }

    public Boolean delete(Long id) {
        var existRestaurant = restaurantRepository.findById(id)
                .orElseThrow(() -> exceptionUtil.buildException(Ex.RESTAURANT_NOT_FOUND_EXCEPTION));
        existRestaurant.setDeleted(true);

        restaurantRepository.save(existRestaurant);
        return Boolean.TRUE;
    }

    public RestaurantResponse getRestaurantById(Long id) {
        var restaurant = restaurantRepository.findById(id)
                .orElseThrow(() -> exceptionUtil.buildException(Ex.RESTAURANT_NOT_FOUND_EXCEPTION));

        return modelMapper.map(restaurant, RestaurantResponse.class);
    }


    public RestaurantResponse findByRestaurantName(String restaurantName) {
        Restaurant foundRestaurant = restaurantRepository.findByRestaurantName(restaurantName);


        return this.modelMapper.map(foundRestaurant, RestaurantResponse.class);
    }

    @Override
    public boolean existsByRestaurantName(String name) {
        return restaurantRepository.existsByRestaurantName(name);
    }

    private RestaurantDTO convertToRestaurantDto(Restaurant restaurant) {
        List<Branch> branchList = restaurant.getBranchs();

        List<GetAllBranchDto> convertedBranchList = branchList.stream()
                .map(branch -> modelMapper.map(branch, GetAllBranchDto.class))
                .collect(Collectors.toList());

        return RestaurantDTO.builder()
                .restaurantName(restaurant.getRestaurantName())
                .adress(restaurant.getAdress())
                .phone(restaurant.getPhone())
                .id(restaurant.getId())
                .branchs(convertedBranchList)
                .build();
    }

}
