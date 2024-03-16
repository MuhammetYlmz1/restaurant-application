package com.muhammet.restaurantapplication.repository;

import com.muhammet.restaurantapplication.model.entity.Food;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Collection;
import java.util.List;

public interface FoodRepository extends JpaRepository<Food,Long> {


     Food findByFoodName(String foodName);

     List<Food> findByBranchIdIn(Collection<Long> branchIds);
     List<Food> findAllByIdIn(List<Long> ids);
}
