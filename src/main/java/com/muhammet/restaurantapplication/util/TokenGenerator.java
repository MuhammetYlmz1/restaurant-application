package com.muhammet.restaurantapplication.util;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.muhammet.restaurantapplication.exception.GenericException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import java.util.Date;

@Component
public class TokenGenerator {

    @Value("${jwt-variables.KEY:restaurant}")
    private String KEY;
    @Value("${jwt-variables.ISSUER:myproject}")
    private String ISSUER;
    @Value("${jwt-variables.EXPIRES_ACCES_TOKEN_MINUTES:15}")
    private Integer EXPIRES_ACCES_TOKEN_MINUTES;

    public String generateToken(Authentication authentication){
        String username = ((UserDetails) authentication.getPrincipal()).getUsername();
        return JWT.create()
                .withSubject(username)
                .withExpiresAt(new Date(System.currentTimeMillis()
                        +(EXPIRES_ACCES_TOKEN_MINUTES*60*1000)))
                .withIssuer(ISSUER)
                .sign(Algorithm.HMAC256(KEY.getBytes()));
    }

    //Verify etme
    public DecodedJWT verifyJWT(String token){
        Algorithm algorithm=Algorithm.HMAC256(KEY.getBytes());
        JWTVerifier verifier=JWT.require(algorithm)
                .build();

        try {
            return verifier.verify(token);
        }
        catch (Exception e){
             throw GenericException.builder()
                     .httpStatus(HttpStatus.BAD_REQUEST)
                     .errorMessage(e.getMessage())
            .build();
        }
    }
}
