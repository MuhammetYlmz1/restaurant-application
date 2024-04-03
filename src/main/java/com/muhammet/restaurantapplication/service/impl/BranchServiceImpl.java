package com.muhammet.restaurantapplication.service.impl;

import com.muhammet.restaurantapplication.exception.BusinessException.Ex;
import com.muhammet.restaurantapplication.exception.ExceptionUtil;
import com.muhammet.restaurantapplication.mapper.BranchMapper;
import com.muhammet.restaurantapplication.model.converter.RestaurantDtoToRestaurantConverter;
import com.muhammet.restaurantapplication.model.dto.FoodDTO;
import com.muhammet.restaurantapplication.model.entity.Branch;
import com.muhammet.restaurantapplication.model.requests.CreateBranchRequest;
import com.muhammet.restaurantapplication.model.requests.UpdateBranchRequest;
import com.muhammet.restaurantapplication.model.responses.GetBranchResponse;
import com.muhammet.restaurantapplication.repository.BranchRepository;
import com.muhammet.restaurantapplication.service.BranchService;
import com.muhammet.restaurantapplication.service.FoodService;
import com.muhammet.restaurantapplication.service.RestaurantService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class BranchServiceImpl implements BranchService {
    private final BranchRepository branchRepository;
    private final ExceptionUtil exceptionUtil;
    private final BranchMapper branchMapper;
    private final FoodService foodService;
    private final RestaurantService restaurantService;
    private final RestaurantDtoToRestaurantConverter restaurantDtoToRestaurantConverter;
    private final ModelMapper modelMapper;

    public List<GetBranchResponse> getAllBranchs(){
        List<Branch> branches= branchRepository.findAll();
        List<Long> branchIds = branches.stream().map(Branch::getId).collect(Collectors.toList());
        List<FoodDTO> foodDTOS=foodService.getByBranchIdIn(branchIds);

        List<GetBranchResponse> branchDtoList = branches.stream().map(branchMapper::map).collect(Collectors.toList());


        Map<Long,List<FoodDTO>> foodDTOMap=foodDTOS
                .stream()
                .collect(Collectors.groupingBy(FoodDTO::getBranchId));

        Map<Long, GetBranchResponse> branchMapping= branchDtoList
                .stream()
                .collect(Collectors.toMap(GetBranchResponse::getId, Function.identity()));

        branchDtoList.forEach(branchDTO -> {
            Long branchId = branchDTO.getId();
            List<FoodDTO> foodsForBranch = foodDTOMap.get(branchId);
            branchDTO.setMenu(foodsForBranch);
        });

        return branchDtoList;
    }

    public GetBranchResponse createBranch(CreateBranchRequest createBranchRequest){

        Branch branch = createBranchRequestToBranch(createBranchRequest);
        Branch savedBranch = branchRepository.save(branch);

        return branchMapper.map(savedBranch);

    }

    public GetBranchResponse updateBranch(UpdateBranchRequest updateBranchRequest){
        var getBranchResponse = getById(updateBranchRequest.getId());

        getBranchResponse.setAdress(updateBranchRequest.getAdress());
        getBranchResponse.setPhone(updateBranchRequest.getPhone());
        getBranchResponse.setDistrict(updateBranchRequest.getDistrict());

        Branch updatedBranch = branchRepository.save(modelMapper.map(getBranchResponse, Branch.class));

        return branchMapper.map(updatedBranch);

    }

    public void deleteBranch(Long id){
        branchRepository.deleteById(id);
    }

    public GetBranchResponse getById(Long id){

        Branch branch = branchRepository.findById(id)
                .orElseThrow(()-> exceptionUtil.buildException(Ex.BRANCH_NOT_FOUND_EXCEPTION));


        return modelMapper.map(branch, GetBranchResponse.class);

    }

    @Override
    public List<GetBranchResponse> getByDistrict(String district) {
        List<Branch> branches = branchRepository.findByDistrict(district);

        return branches
                .stream()
                .map(branchMapper::map).collect(Collectors.toList());
    }

    private Branch createBranchRequestToBranch(CreateBranchRequest createBranchRequest){
        return Branch.builder()
                .phone(createBranchRequest.getPhone())
                .district(createBranchRequest.getDistrict())
                .adress(createBranchRequest.getAdress())
                .restaurantId(restaurantDtoToRestaurantConverter.map(restaurantService.getRestaurantById(createBranchRequest.getRestaurantId())))
                .menu(null)
                .orders(null)
                .build();
    }

}
