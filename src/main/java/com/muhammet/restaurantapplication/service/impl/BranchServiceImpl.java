package com.muhammet.restaurantapplication.service.impl;

import com.muhammet.restaurantapplication.model.dto.BranchDTO;
import com.muhammet.restaurantapplication.model.dto.FoodDTO;
import com.muhammet.restaurantapplication.model.requests.CreateBranchRequest;
import com.muhammet.restaurantapplication.model.requests.UpdateBranchRequest;
import com.muhammet.restaurantapplication.exception.BranchNotFoundException;
import com.muhammet.restaurantapplication.mapper.BranchMapper;
import com.muhammet.restaurantapplication.model.entity.Branch;
import com.muhammet.restaurantapplication.repository.BranchRepository;
import com.muhammet.restaurantapplication.service.BranchService;
import com.muhammet.restaurantapplication.service.FoodService;
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
    private final BranchRepository repository;
    private final ModelMapper modelMapper;
    private final BranchMapper branchMapper;
    private final FoodService foodService;

    public List<BranchDTO> getAllBranchs(){
        List<Branch> branches=repository.findAll();
        List<Long> branchIds = branches.stream().map(Branch::getId).collect(Collectors.toList());
        List<FoodDTO> foodDTOS=foodService.getByBranchIdIn(branchIds);

        List<BranchDTO> branchDtoList = branches.stream().map(branch -> branchMapper.map(branch)).collect(Collectors.toList());


        Map<Long,List<FoodDTO>> foodDTOMap=foodDTOS
                .stream()
                .collect(Collectors.groupingBy(FoodDTO::getBranchId));

        Map<Long, BranchDTO> branchMapping= branchDtoList
                .stream()
                .collect(Collectors.toMap(BranchDTO::getId, Function.identity()));

        branchDtoList.forEach(branchDTO -> {
            Long branchId = branchDTO.getId();
            List<FoodDTO> foodsForBranch = foodDTOMap.get(branchId);
            branchDTO.setMenu(foodsForBranch);
        });



        return branchDtoList;
    }

    public BranchDTO createBranch(CreateBranchRequest createBranchRequest){


        Branch branch=this.modelMapper.map(createBranchRequest,Branch.class);
        Branch savedBranch = repository.save(branch);

        return branchMapper.map(savedBranch);

    }
    public BranchDTO updateBranch(UpdateBranchRequest updateBranchRequest){
        var branch=repository.findById(updateBranchRequest.getId()).orElseThrow(() -> new BranchNotFoundException("branch not found"));

        branch.setAdress(updateBranchRequest.getAdress());
        branch.setPhone(updateBranchRequest.getPhone());
        branch.setDistrict(updateBranchRequest.getDistrict());

        Branch updatedBranch = repository.save(branch);

        return branchMapper.map(updatedBranch);

    }

    public void deleteBranch(Long id){
        repository.deleteById(id);
    }

    public BranchDTO getById(Long id){

        var branch=repository.findById(id)
                .orElseThrow(()->new BranchNotFoundException("Böyle bir Branch Id yok"));
        BranchDTO branchDTO=this.modelMapper.map(branch,BranchDTO.class);

        return branchDTO;
    }

    @Override
    public List<BranchDTO> getByDistrict(String district) {
        List<Branch> branches = repository.findByDistrict(district);

        List<BranchDTO> branchDTO = branches
                .stream()
                .map(branch -> branchMapper.map(branch)).collect(Collectors.toList());


        return branchDTO;
    }


}
