package com.muhammet.restaurantapplication.service.impl;

import com.muhammet.restaurantapplication.dto.RestaurantDTO;
import com.muhammet.restaurantapplication.dto.requests.CreateRestaurantRequest;
import com.muhammet.restaurantapplication.dto.requests.UpdateRestaurantRequest;
import com.muhammet.restaurantapplication.dto.responses.GetAllBranchResponse;
import com.muhammet.restaurantapplication.exception.RestaurantNotFoundException;
import com.muhammet.restaurantapplication.model.Branch;
import com.muhammet.restaurantapplication.model.Restaurant;
import com.muhammet.restaurantapplication.repository.BranchRepository;
import com.muhammet.restaurantapplication.repository.RestaurantRepository;
import com.muhammet.restaurantapplication.service.RestaurantService;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class RestaurantServiceImpl implements RestaurantService {

    private final RestaurantRepository restaurantRepository;
    private final BranchRepository branchRepository;
    private final ModelMapper modelMapper;

    public RestaurantServiceImpl(RestaurantRepository restaurantRepository, BranchRepository branchRepository, ModelMapper modelMapper) {

        this.restaurantRepository = restaurantRepository;
        this.branchRepository = branchRepository;
        this.modelMapper = modelMapper;
    }

    public List<RestaurantDTO> getAllRestaurants(){


      /*  List<Restaurant> restaurants=restaurantRepository.findAll();
        List<RestaurantDTO> restaurantDTOS=new ArrayList<>();
        restaurants.forEach(restaurant ->{
            RestaurantDTO restaurantDTO=new RestaurantDTO();
            restaurantDTO.setRestaurantName(restaurant.getRestaurantName());
            restaurantDTO.setAdress(restaurant.getAdress());
            restaurantDTO.setBranchs(restaurant.getBranchs().stream().map(Branch::getAdress).collect(Collectors.toList()));
            restaurantDTO.setPhone(restaurant.getPhone());
            restaurantDTOS.add(restaurantDTO);
        });*/

        return restaurantRepository.findAll().stream()
                .map(restaurants->this.modelMapper.map(restaurants,RestaurantDTO.class)).collect(Collectors.toList());

        /*return restaurantRepository.findAll().stream()
                .map(x-> convertToRestaurantDto(x)).collect(Collectors.toList());*/
    }

    public void createRestaurant(CreateRestaurantRequest restaurantRequest) throws Exception {
        if (restaurantRequest.getAdress().equals("") || restaurantRequest.getRestaurantName().equals("") || restaurantRequest.getPhone().equals("")){
            throw new Exception("Alanları doldurunuz!!");
        }
        Restaurant restaurant= modelMapper.map(restaurantRequest,Restaurant.class);
        restaurantRepository.save(restaurant);
    }

    public RestaurantDTO updateRestaurant(UpdateRestaurantRequest updateRestaurantRequest){
        Restaurant updating=this.restaurantRepository.findById(updateRestaurantRequest.getId())
                .orElseThrow(()->new RestaurantNotFoundException("Böyle bir restorant bulunmamaktadır."));

        updating.setPhone(updateRestaurantRequest.getPhone());
        updating.setAdress(updateRestaurantRequest.getAdress());
        updating.setRestaurantName(updateRestaurantRequest.getRestaurantName());

        //var restaurant=modelMapper.map(updating,Restaurant.class);

        Restaurant restaurant= restaurantRepository.save(updating);
        return convertToRestaurantDto(restaurant);

    }

    public void deleteRestaurant(Long id){
        restaurantRepository.deleteById(id);

    }


    public RestaurantDTO getRestaurantById(Long id){
        var restaurant=restaurantRepository.findById(id)
                .orElseThrow(()->new RestaurantNotFoundException("Böyle bir Id bulunmamaktadır"));

        RestaurantDTO restaurantDTO= modelMapper.map(restaurant,RestaurantDTO.class);
        return restaurantDTO;
    }

    public RestaurantDTO findByRestaurandName(String restaurantName){
        Restaurant foundRestaurant=restaurantRepository.findByRestaurantName(restaurantName);

        var restaurantDto=this.modelMapper.map(foundRestaurant,RestaurantDTO.class);


        return restaurantDto;
    }


    public RestaurantDTO convertToRestaurantDto(Restaurant restaurant){
        List<Branch> branchList = restaurant.getBranchs();

        List<GetAllBranchResponse> convertedList = branchList.stream()
                .map(branch -> modelMapper.map(branch, GetAllBranchResponse.class))
                .collect(Collectors.toList());

        return RestaurantDTO.builder()
                .restaurantName(restaurant.getRestaurantName())
                .adress(restaurant.getAdress())
                .phone(restaurant.getPhone())
                .id(restaurant.getId())
                .branchs(convertedList)
                .build();
    }
  /*  public boolean checkCreateRestaurantRequestFields(CreateRestaurantRequest request){

        if (request.getRestaurantName().isEmpty()|| request.getAdress().isEmpty()||request.getPhone().isEmpty()){

        }


    }*/

}
