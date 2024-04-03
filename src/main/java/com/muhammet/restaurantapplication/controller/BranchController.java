package com.muhammet.restaurantapplication.controller;

import com.muhammet.restaurantapplication.model.responses.GetBranchResponse;
import com.muhammet.restaurantapplication.model.entity.Branch;
import com.muhammet.restaurantapplication.model.requests.CreateBranchRequest;
import com.muhammet.restaurantapplication.model.requests.UpdateBranchRequest;
import com.muhammet.restaurantapplication.service.BranchService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/branch")
@RequiredArgsConstructor
public class BranchController {
    private final BranchService branchService;

    @GetMapping
    public List<GetBranchResponse> getAll(){
        return branchService.getAllBranchs();
    }

    @PostMapping
    public ResponseEntity<GetBranchResponse> create(@Valid @RequestBody CreateBranchRequest createBranchRequest){
        return ResponseEntity.ok(branchService.createBranch(createBranchRequest));
    }
    @PutMapping
    public ResponseEntity<GetBranchResponse> update(@Valid @RequestBody UpdateBranchRequest updateBranchRequest){
        return ResponseEntity.ok(branchService.updateBranch(updateBranchRequest));
    }
    @DeleteMapping
    public void deleteBranch(@Valid @PathVariable Long id){
        branchService.deleteBranch(id);
    }

    @GetMapping("{id}")
    public ResponseEntity<GetBranchResponse> getById(@Valid @PathVariable Long id){
        return ResponseEntity.ok(branchService.getById(id));
    }

    @GetMapping("/getByDistrict/{district}")
    public ResponseEntity<List<GetBranchResponse>> getByDistrict(@PathVariable String district){
        return ResponseEntity.ok(branchService.getByDistrict(district));
    }


}
