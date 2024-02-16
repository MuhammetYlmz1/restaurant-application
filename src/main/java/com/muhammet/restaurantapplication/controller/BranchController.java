package com.muhammet.restaurantapplication.controller;

import com.muhammet.restaurantapplication.model.dto.BranchDTO;
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
    public List<BranchDTO> getAll(){
        return branchService.getAllBranchs();
    }

    @PostMapping
    public ResponseEntity<BranchDTO> create(@Valid @RequestBody CreateBranchRequest createBranchRequest){
        return ResponseEntity.ok(branchService.createBranch(createBranchRequest));
    }
    @PutMapping
    public ResponseEntity<BranchDTO> update(@Valid @RequestBody UpdateBranchRequest updateBranchRequest){
        return ResponseEntity.ok(branchService.updateBranch(updateBranchRequest));
    }
    @DeleteMapping
    public void deleteBranch(@Valid @PathVariable Long id){
        branchService.deleteBranch(id);
    }

    @GetMapping("{id}")
    public BranchDTO getById(@Valid @PathVariable Long id){
        return branchService.getById(id);
    }

    @GetMapping("/getByDistrict/{district}")
    public ResponseEntity<List<BranchDTO>> getByDistrict(@PathVariable String district){
        return ResponseEntity.ok(branchService.getByDistrict(district));
    }


}
