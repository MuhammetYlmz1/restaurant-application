package com.muhammet.restaurantapplication.dto;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.muhammet.restaurantapplication.model.Food;
import com.muhammet.restaurantapplication.model.Restaurant;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class BranchDTO {

    private Long id;


    private String adress;

    private String phone;

    private String district;

    private String restaurantName;

    private List<FoodDTO> menu;

}
