package com.muhammet.restaurantapplication.service.impl;

import com.muhammet.restaurantapplication.dto.FoodDTO;
import com.muhammet.restaurantapplication.dto.requests.CreateFoodRequest;
import com.muhammet.restaurantapplication.dto.requests.UpdateFoodRequest;
import com.muhammet.restaurantapplication.exception.BranchNotFoundException;
import com.muhammet.restaurantapplication.exception.FoodNotFoundException;
import com.muhammet.restaurantapplication.model.Branch;
import com.muhammet.restaurantapplication.model.Food;
import com.muhammet.restaurantapplication.repository.BranchRepository;
import com.muhammet.restaurantapplication.repository.FoodRepository;
import com.muhammet.restaurantapplication.service.FoodService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;


@Service
@RequiredArgsConstructor
public class FoodServiceImpl implements FoodService {

    private final FoodRepository repository;
    private final ModelMapper modelMapper;
    private final BranchRepository branchRepository;





    public List<FoodDTO> getAllFoods() {
        List<Food> foods = repository.findAll();

        var getAllFoodsResponses=foods.
                stream()
                .map(food -> this.modelMapper.map(food, FoodDTO.class))
                .collect(Collectors.toList());


        return getAllFoodsResponses;
    }

    public void createFood(CreateFoodRequest createFoodRequest) {


        /*Food food=Food.builder()
                .foodName(createFoodRequest.getFoodName())
                .price(createFoodRequest.getPrice())
                .build();
        var branch= branchService.getById(createFoodRequest.getBranchId());

        food.setBranch(branch);
        repository.save(food);*/

        var branch=branchRepository.getById(createFoodRequest.getBranchId());
        if (branch == null) {
            throw new BranchNotFoundException("Böyle bir şube yoktur");
        }

        Food food=Food.builder()
                .foodName(createFoodRequest.getFoodName())
                .price(createFoodRequest.getPrice())
                .branch(modelMapper.map(branch, Branch.class))
                .build();

        repository.save(food);


     /*   var saveFood=repository.save(food);
        saveFood.setBranch(branch);
        return saveFood;*/



        //repository.save(food);

    }
    public void updateFood(UpdateFoodRequest updateFoodRequest){

        var foodDTO=getFoodById(updateFoodRequest.getId());
        //  var branch=branchService.getById(food.getBranch().getId());
        foodDTO.setFoodName(updateFoodRequest.getFoodName());
        foodDTO.setPrice(updateFoodRequest.getPrice());

        Food food= modelMapper.map(foodDTO,Food.class);

        repository.save(food);

    }

    public void deleteByFoodId(Long id){
        FoodDTO foodDTO=getFoodById(id);

        repository.deleteById(id);
    }

    public FoodDTO findByFoodName(String name){
        Food food=repository.findByFoodName(name);

        var result=this.modelMapper.map(food,FoodDTO.class);
        return result;

    }

    public FoodDTO getFoodById(Long id){
        Food food=repository.findById(id).orElseThrow(()->new FoodNotFoundException("Böyle bir yemek bulunamadı"));
        var foodDTO=modelMapper.map(food,FoodDTO.class);

        return foodDTO;
    }

    @Override
    public List<FoodDTO> getByBranchIdIn(Collection<Long> branchIds) {

        //List<FoodDTO> foodDTOS=
        List<Food> foods=repository.findByBranchIdIn(branchIds);
        return foods
                .stream()
                .map(food -> modelMapper.map(food,FoodDTO.class))
                .collect(Collectors.toList());

    }

    private boolean isValid(CreateFoodRequest request){
        if (!request.getFoodName().isBlank() &&
            request.getPrice() != null && request.getPrice()>0){
            return true;
        }
        return false;

    }

}
