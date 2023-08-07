package com.muhammet.restaurantapplication.dto.requests;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;


@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CreateFoodRequest {

    @NotBlank(message = "Yiyecek ismi boş olmamalıdır")
    private String foodName;
    @NotNull
    @Min(value = 0,message = "Fiyat degeri 0 dan buyuk olmalidir")
    private Double price;
    @NotNull
    private Long branchId;

}
