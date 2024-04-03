package com.muhammet.restaurantapplication.comp.jwt;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Configuration
@ConfigurationProperties(prefix = "token")
@NoArgsConstructor
@Data
public class TokenConfiguration {

    private String issuer;
    private Integer accessExpireMinute;

}
