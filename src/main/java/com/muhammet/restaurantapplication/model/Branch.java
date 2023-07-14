package com.muhammet.restaurantapplication.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Builder
@Table(name = "branchs")
public class Branch {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;



    @JoinColumn(name="adress")
    private String adress;

    @JoinColumn(name = "phone")
    private String phone;

    @JoinColumn(name = "district")
    private String district;

    //@JsonBackReference
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="restaurant_id")
    private Restaurant restaurantId;

    //@JsonManagedReference
    @OneToMany(fetch = FetchType.LAZY,cascade = CascadeType.ALL,mappedBy = "branch")
    private List<Food> menu;


}
