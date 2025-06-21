package com.muhammet.restaurantapplication.model.request;

import lombok.*;

import javax.validation.constraints.NotNull;
import java.util.List;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CreateOrderRequest {

    @NotNull
    private Long userId;
    @NotNull
    private Long branchId;
    private String name;
    private String surname;
    private String note;
    private String phone;
    private List<Long> foods;
}
