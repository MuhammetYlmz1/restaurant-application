package com.muhammet.restaurantapplication.mapper;

import com.muhammet.restaurantapplication.model.responses.GetBranchResponse;
import com.muhammet.restaurantapplication.model.entity.Branch;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class BranchMapper implements BaseMapper<Branch, GetBranchResponse> {
    private final ModelMapper modelMapper;

    @Override
    public GetBranchResponse map(Branch branch) {
        return modelMapper.map(branch, GetBranchResponse.class);
    }
}
