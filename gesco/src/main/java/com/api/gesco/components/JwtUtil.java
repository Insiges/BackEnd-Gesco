package com.api.gesco.components;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class JwtUtil {
    @Value("${api.security.token.secret}")
    private String SECRET;

    public DecodedJWT decodeToken(String token) {
        String tokenJwt = token.substring(7);
        return JWT.require(Algorithm.HMAC256(SECRET))
                .build()
                .verify(tokenJwt);
    }

    public String getEmailFromToken(String token) {
        DecodedJWT decodedJWT = decodeToken(token);
        return decodedJWT.getClaim("sub").asString();
    }
}
