package com.muhammet.restaurantapplication.service;

import com.muhammet.restaurantapplication.model.request.CreateBranchRequest;
import com.muhammet.restaurantapplication.model.request.UpdateBranchRequest;
import com.muhammet.restaurantapplication.model.response.GetBranchResponse;

import java.util.List;



public interface BranchService {

     List<GetBranchResponse> getAllBranchs();

     GetBranchResponse createBranch(CreateBranchRequest createBranchRequest);
     GetBranchResponse updateBranch(UpdateBranchRequest updateBranchRequest);

     void deleteBranch(Long id);

     GetBranchResponse getById(Long id);

     List<GetBranchResponse>  getByDistrict(String district);


}
