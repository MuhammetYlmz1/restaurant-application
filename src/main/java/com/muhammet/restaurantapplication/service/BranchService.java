package com.muhammet.restaurantapplication.service;

import com.muhammet.restaurantapplication.dto.BranchDTO;
import com.muhammet.restaurantapplication.dto.requests.CreateBranchRequest;
import com.muhammet.restaurantapplication.dto.requests.UpdateBranchRequest;

import java.util.List;



public interface BranchService {

     List<BranchDTO> getAllBranchs();

     BranchDTO createBranch(CreateBranchRequest createBranchRequest);
     BranchDTO updateBranch(UpdateBranchRequest updateBranchRequest);

     void deleteBranch(Long id);

     BranchDTO getById(Long id);

     List<BranchDTO>  getByDistrict(String district);


}
