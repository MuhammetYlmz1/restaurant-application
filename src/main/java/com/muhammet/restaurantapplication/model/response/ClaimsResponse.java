package com.muhammet.restaurantapplication.model.response;

import lombok.*;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ClaimsResponse {

    private String iss;
    private String sub;
    private String aud;
    private String exp;
    private String nbf;
    private String iat;
    private String jti;
    private String type;
}
