package com.muhammet.restaurantapplication.model.dto;

import com.muhammet.restaurantapplication.model.enums.UserRole;
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

    private Long id;
    @NotBlank
    private String username;
    private String name;
    private String password;
    private UserRole role;
    @NotBlank
    @Email
    private String email;

}
