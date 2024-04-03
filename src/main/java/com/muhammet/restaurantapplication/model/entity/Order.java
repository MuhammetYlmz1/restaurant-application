package com.muhammet.restaurantapplication.model.entity;


import lombok.*;

import javax.persistence.*;
import java.util.List;

@Getter
@Setter
@Builder
@Entity
@Table(name = "orders")
@AllArgsConstructor
@NoArgsConstructor
public class Order extends BaseEntity{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "branch_id")
    private Branch branch;

    @Column
    private String note;

    @Column
    private String name;

    @Column
    private String surname;

    @Column
    private String phone;

    @Column
    private Integer totalProduct;

    @Column
    private Double totalPrice;

    @OneToMany(mappedBy = "order", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<OrderFood> orderFoods;
}