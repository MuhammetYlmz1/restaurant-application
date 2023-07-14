package com.muhammet.restaurantapplication.service;

import com.muhammet.restaurantapplication.dto.BranchDTO;
import com.muhammet.restaurantapplication.dto.requests.CreateBranchRequest;
import com.muhammet.restaurantapplication.dto.requests.UpdateBranchRequest;
import com.muhammet.restaurantapplication.dto.responses.GetAllBranchResponse;
import com.muhammet.restaurantapplication.exception.BranchNotFoundException;
import com.muhammet.restaurantapplication.model.Branch;
import com.muhammet.restaurantapplication.repository.BranchRepository;
import org.hibernate.sql.Update;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;



public interface BranchService {

     List<BranchDTO> getAllBranchs();

     CreateBranchRequest createBranch(CreateBranchRequest createBranchRequest);
     UpdateBranchRequest updateBranch(UpdateBranchRequest updateBranchRequest);

     void deleteBranch(Long id);

     BranchDTO getById(Long id);

}
