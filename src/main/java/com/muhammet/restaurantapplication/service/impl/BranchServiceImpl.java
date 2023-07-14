package com.muhammet.restaurantapplication.service.impl;

import com.muhammet.restaurantapplication.dto.BranchDTO;
import com.muhammet.restaurantapplication.dto.requests.CreateBranchRequest;
import com.muhammet.restaurantapplication.dto.requests.UpdateBranchRequest;
import com.muhammet.restaurantapplication.exception.BranchNotFoundException;
import com.muhammet.restaurantapplication.model.Branch;
import com.muhammet.restaurantapplication.repository.BranchRepository;
import com.muhammet.restaurantapplication.service.BranchService;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class BranchServiceImpl implements BranchService {
    private final BranchRepository repository;
    private final ModelMapper modelMapper;

    public BranchServiceImpl(BranchRepository repository, ModelMapper modelMapper) {
        this.repository = repository;
        this.modelMapper = modelMapper;
    }

    public List<BranchDTO> getAllBranchs(){
        List<Branch> branches=repository.findAll();

        var branchDTOS= branches
                .stream()
                .map(branch->this.modelMapper.map(branch, BranchDTO.class))
                .collect(Collectors.toList());
        return branchDTOS;


    }

    public CreateBranchRequest createBranch(CreateBranchRequest createBranchRequest){
        //fieldControl(createBranchRequest);


        Branch branch=this.modelMapper.map(createBranchRequest,Branch.class);
        repository.save(branch);
        return createBranchRequest;

    }
    public UpdateBranchRequest updateBranch(UpdateBranchRequest updateBranchRequest){
        var branch=repository.findById(updateBranchRequest.getId()).orElseThrow();

        branch.setAdress(updateBranchRequest.getAdress());
        branch.setPhone(updateBranchRequest.getPhone());
        branch.setDistrict(updateBranchRequest.getDistrict());

        repository.save(branch);

        var result=this.modelMapper.map(branch,UpdateBranchRequest.class);
        return result;

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

    /*public boolean fieldControl(CreateBranchRequest request){
        if (request.getAdress().equals("")|| request. ) {

        }

    }*/

    /*public BranchDTO convertToBranchDTO(){

    }*/

}
