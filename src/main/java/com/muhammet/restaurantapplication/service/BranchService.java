package com.muhammet.restaurantapplication.service;

import com.muhammet.restaurantapplication.model.responses.GetBranchResponse;
import com.muhammet.restaurantapplication.model.entity.Branch;
import com.muhammet.restaurantapplication.model.requests.CreateBranchRequest;
import com.muhammet.restaurantapplication.model.requests.UpdateBranchRequest;

import java.util.List;



public interface BranchService {

     List<GetBranchResponse> getAllBranchs();

     GetBranchResponse createBranch(CreateBranchRequest createBranchRequest);
     GetBranchResponse updateBranch(UpdateBranchRequest updateBranchRequest);

     void deleteBranch(Long id);

     Branch getById(Long id);

     List<GetBranchResponse>  getByDistrict(String district);


}
