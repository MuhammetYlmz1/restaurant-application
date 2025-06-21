package com.muhammet.restaurantapplication.service.impl;

import com.muhammet.restaurantapplication.exception.BusinessException.Ex;
import com.muhammet.restaurantapplication.exception.ExceptionUtil;
import com.muhammet.restaurantapplication.model.dto.BranchDto;
import com.muhammet.restaurantapplication.model.dto.FoodDTO;
import com.muhammet.restaurantapplication.model.entity.Branch;
import com.muhammet.restaurantapplication.model.entity.Food;
import com.muhammet.restaurantapplication.model.request.CreateFoodRequest;
import com.muhammet.restaurantapplication.model.request.UpdateFoodRequest;
import com.muhammet.restaurantapplication.repository.BranchRepository;
import com.muhammet.restaurantapplication.repository.FoodRepository;
import com.muhammet.restaurantapplication.service.FoodService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;


@Service
@RequiredArgsConstructor
public class FoodServiceImpl implements FoodService {

    private final FoodRepository repository;
    private final ModelMapper modelMapper;
    private final BranchRepository branchRepository;
    private final ExceptionUtil exceptionUtil;

    public List<FoodDTO> getAllFoods() {
        List<Food> foods = repository.findAll();

        var getAllFoodsResponses=foods.
                stream()
                .map(this::foodDtoConverter)
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
            throw exceptionUtil.buildException(Ex.BRANCH_NOT_FOUND_EXCEPTION);
        }

        Food food=Food.builder()
                .foodName(createFoodRequest.getFoodName())
                .price(createFoodRequest.getPrice())
                .branch(modelMapper.map(branch, Branch.class))
                .build();

        repository.save(food);

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
        Food food=repository.findById(id).orElseThrow(()->exceptionUtil.buildException(Ex.FOOD_NOT_FOUND_EXCEPTION));
        var foodDTO=modelMapper.map(food,FoodDTO.class);

        return foodDTO;
    }

    @Override
    public List<FoodDTO> getByBranchIdIn(Collection<Long> branchIds) {

        List<Food> foods=repository.findByBranchIdIn(branchIds);
        return foods
                .stream()
                .map(food -> modelMapper.map(food,FoodDTO.class))
                .collect(Collectors.toList());

    }

    @Override
    public List<Food> findAllByIdIn(List<Long> ids) {
        return repository.findAllByIdIn(ids);
    }

    @Override
    public FoodDTO findById(Long id) {
        Optional<Food> food = repository.findById(id);
        if (Objects.isNull(food)){
            throw exceptionUtil.buildException(Ex.FOOD_NOT_FOUND_EXCEPTION);
        }

        return convertToFoodDTO(food.get());
    }

    /*private boolean isValid(CreateFoodRequest request){
        if (!request.getFoodName().isBlank() &&
            request.getPrice() != null && request.getPrice()>0){
            return true;
        }
        return false;

    }*/

    private FoodDTO convertToFoodDTO(Food food){
        return FoodDTO.builder()
                .id(food.getId())
                .foodName(food.getFoodName())
                .price(food.getPrice())
                .branchDto(BranchDto
                        .builder()
                        .phone(food.getBranch().getPhone())
                        .district(food.getBranch().getDistrict())
                        .restaurantName(food.getBranch().getRestaurantId().getRestaurantName())
                        .adress(food.getBranch().getAdress())
                        .build())
                .build();
    }

    private FoodDTO foodDtoConverter(Food food){
        return FoodDTO.builder()
                .branchDto(convertBrancDto(food.getBranch()))
                .price(food.getPrice())
                .foodName(food.getFoodName())
                .id(food.getId())
                .build();
    }

    private BranchDto convertBrancDto(Branch branch) {
        return BranchDto.builder()
                .district(branch.getDistrict())
                .restaurantName(branch.getRestaurantId().getRestaurantName())
                .adress(branch.getAdress())
                .phone(branch.getPhone())
                .build();

    }

}
