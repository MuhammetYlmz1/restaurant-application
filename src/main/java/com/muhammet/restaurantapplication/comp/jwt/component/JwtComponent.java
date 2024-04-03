package com.muhammet.restaurantapplication.comp.jwt.component;

import com.muhammet.restaurantapplication.comp.jwt.TokenConfiguration;
import com.muhammet.restaurantapplication.comp.jwt.constants.TokenClaims;
import com.muhammet.restaurantapplication.comp.jwt.constants.TokenTypes;
import com.muhammet.restaurantapplication.exception.AuthorizationException;
import io.jsonwebtoken.*;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.time.DateUtils;
import org.springframework.stereotype.Component;

import java.security.KeyPair;
import java.security.NoSuchAlgorithmException;
import java.util.Date;
import java.util.Map;
import java.util.UUID;

@Component
@Slf4j
public class JwtComponent {

    private final TokenConfiguration tokenConfiguration;
    private final KeyPair keyPair;

    public JwtComponent(KeyGeneratorComponent keyGeneratorComponent,
                        TokenConfiguration tokenConfiguration) throws NoSuchAlgorithmException {
        this.tokenConfiguration = tokenConfiguration;
        this.keyPair = keyGeneratorComponent.generateKeyPair();
    }

    public String generateAccessToken(Map<String, Object> claims, String subject){
        Date expirationDate = DateUtils.addMinutes(new Date(), tokenConfiguration.getAccessExpireMinute());
        claims.put(TokenClaims.TYPE.getValue(), TokenTypes.BEARER.name());
        return this.generateToken(claims, expirationDate, subject);
    }

    private String generateToken(Map<String, Object> claims, Date expirationDate, String subject) {
        return Jwts.builder()
                .setClaims(claims)
                .setExpiration(expirationDate)
                .setSubject(subject)
                .setIssuedAt(new Date())
                .setId(UUID.randomUUID().toString())
                .setIssuer(tokenConfiguration.getIssuer())
                .signWith(keyPair.getPrivate(), SignatureAlgorithm.RS256).compact();

    }

    public Claims verifyToken(String token) {
        try {
            return Jwts.parserBuilder()
                    .setSigningKey(keyPair.getPublic())
                    .build()
                    .parseClaimsJws(token)
                    .getBody();
        }catch (MalformedJwtException | ExpiredJwtException e ) {
            log.info("JWT verification failed: {}", e.getMessage());
            throw new AuthorizationException("token couldn't verified","");
        }
    }
}
