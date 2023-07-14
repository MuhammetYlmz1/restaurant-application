package com.muhammet.restaurantapplication.controller;

import com.muhammet.restaurantapplication.dto.BranchDTO;
import com.muhammet.restaurantapplication.dto.requests.CreateBranchRequest;
import com.muhammet.restaurantapplication.dto.requests.UpdateBranchRequest;
import com.muhammet.restaurantapplication.dto.responses.GetAllBranchResponse;
import com.muhammet.restaurantapplication.model.Branch;
import com.muhammet.restaurantapplication.repository.BranchRepository;
import com.muhammet.restaurantapplication.service.BranchService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api/branch/")
public class BranchController {

    private final BranchService branchService;

    public BranchController(BranchService branchService) {
        this.branchService = branchService;
    }

    @GetMapping
    public List<BranchDTO> getAll(){
        return branchService.getAllBranchs();
    }

    @PostMapping
    public ResponseEntity<CreateBranchRequest> createBranchRequestResponseEntity(@Valid @RequestBody CreateBranchRequest createBranchRequest){
        return ResponseEntity.ok(branchService.createBranch(createBranchRequest));
    }
    @PutMapping
    public ResponseEntity<UpdateBranchRequest> updateBranchRequestResponseEntity(@Valid @RequestBody UpdateBranchRequest updateBranchRequest){
        return ResponseEntity.ok(branchService.updateBranch(updateBranchRequest));
    }
    @DeleteMapping
    public void deleteBranc(@Valid @PathVariable Long id){
        branchService.deleteBranch(id);
    }

    @GetMapping("{id}")
    public BranchDTO getById(@Valid @PathVariable Long id){
        return branchService.getById(id);
    }



}
