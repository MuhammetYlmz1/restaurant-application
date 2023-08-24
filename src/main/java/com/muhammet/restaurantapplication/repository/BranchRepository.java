package com.muhammet.restaurantapplication.repository;

import com.muhammet.restaurantapplication.model.Branch;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface BranchRepository extends JpaRepository<Branch,Long> {

    List<Branch> findByDistrict(String district);

    List<Branch> findByRestaurantId(Long id);



}
