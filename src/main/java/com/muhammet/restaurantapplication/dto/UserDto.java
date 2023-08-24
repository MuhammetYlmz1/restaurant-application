package com.muhammet.restaurantapplication.dto;

import com.muhammet.restaurantapplication.model.UserRole;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class UserDto {
    @NotBlank
    private String username;
    private String name;
    private UserRole role;
    @NotBlank
    @Email
    private String email;

}
