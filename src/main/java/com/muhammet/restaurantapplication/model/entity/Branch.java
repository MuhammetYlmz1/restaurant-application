package com.muhammet.restaurantapplication.model.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import javax.persistence.*;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Builder
@Table(name = "branchs")
public class Branch extends BaseEntity{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name="adress")
    private String adress;

    @Column(name = "phone")
    private String phone;

    @Column(name = "district")
    private String district;


    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="restaurant_id")
    @JsonIgnore
    private Restaurant restaurantId;


    @OneToMany(fetch = FetchType.LAZY,cascade = CascadeType.ALL,mappedBy = "branch")
    private List<Food> menu;

    @OneToMany(fetch = FetchType.LAZY,cascade = CascadeType.ALL,mappedBy = "branch")
    private List<Order> orders;
}
