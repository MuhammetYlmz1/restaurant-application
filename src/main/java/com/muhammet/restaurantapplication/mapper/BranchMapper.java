package com.muhammet.restaurantapplication.mapper;

import com.muhammet.restaurantapplication.dto.BranchDTO;
import com.muhammet.restaurantapplication.model.Branch;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class BranchMapper implements BaseMapper<Branch, BranchDTO> {
    private final ModelMapper modelMapper;

    @Override
    public BranchDTO map(Branch branch) {
        return modelMapper.map(branch, BranchDTO.class);
    }
}
