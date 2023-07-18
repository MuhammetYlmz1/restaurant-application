package com.muhammet.restaurantapplication.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "restaurants")
@Entity
public class Restaurant {

    @Id
    @Column(name = "id")
    private Long id;

    @Column(name="restaurant_name")
    private String restaurantName;

    @Column(name = "phone")
    private String phone;

    @Column(name="adress")
    private String adress;

    //@JsonManagedReference
    @OneToMany(fetch = FetchType.LAZY,cascade = CascadeType.ALL,mappedBy = "restaurantId")
    private List<Branch> branchs;

}
