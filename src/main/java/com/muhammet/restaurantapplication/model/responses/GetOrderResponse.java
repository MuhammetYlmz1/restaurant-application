package com.muhammet.restaurantapplication.model.responses;

import com.muhammet.restaurantapplication.model.dto.BranchDto;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class GetOrderResponse {
    private Long id;
    private BranchDto branchDTO;
    private String note;
    private String name;
    private String surname;
    private Integer totalProduct;
    private Double totalPrice;
    private LocalDateTime orderDate;

    /*
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
    private Double totalPrice;

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "order_foods",
            joinColumns = @JoinColumn(name = "order_id"),
            inverseJoinColumns = @JoinColumn(name = "food_id"))
    private List<Food> foods;
     */

}
