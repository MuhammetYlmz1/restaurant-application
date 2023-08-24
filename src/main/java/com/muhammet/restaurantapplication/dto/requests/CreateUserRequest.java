package com.muhammet.restaurantapplication.dto.requests;

import com.muhammet.restaurantapplication.model.UserRole;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CreateUserRequest {

    private String name;

    private String surname;

    private String userName;

    private String email;

    private String password;

    private UserRole role;

}
