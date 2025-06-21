package com.muhammet.restaurantapplication.model.request;

import com.muhammet.restaurantapplication.model.enums.UserRole;
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
