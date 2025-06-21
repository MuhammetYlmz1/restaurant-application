package com.muhammet.restaurantapplication.model.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.muhammet.restaurantapplication.validator.PhoneNumber;
import lombok.*;

import javax.persistence.*;
import java.util.List;
@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "restaurants")
@Entity
public class Restaurant extends BaseEntity {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name="restaurant_name")
    private String restaurantName;

    @Column(name = "phone")
    @PhoneNumber
    private String phone;

    @Column(name="adress")
    private String adress;

    @JsonIgnore
    @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL, mappedBy = "restaurantId")
    private List<Branch> branchs;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "restaurant", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<RestaurantReview> reviews;

    private boolean deleted;
}
