package com.api.gesco.infra.security;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.stereotype.Service;

import com.api.gesco.model.logins.LoginEscola;
import com.api.gesco.model.logins.LoginProfessor;
import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTCreationException;
import com.auth0.jwt.exceptions.JWTVerificationException;

import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.util.Date;
import java.util.stream.Collectors;

@Service
public class TokenService {
    @Value("${api.security.token.secret}")
    private String secret;

    public String gerarTokenProfessor(LoginProfessor professorLogin) {
        try {
            Algorithm algorithm = Algorithm.HMAC256(secret);

            String token = JWT.create()
                .withIssuer("gesco")
                .withSubject(professorLogin.getEmail())
                .withClaim("roles", professorLogin.getAuthorities().stream()
                    .map(GrantedAuthority::getAuthority)
                    .collect(Collectors.toList()))  
                .withExpiresAt(generateExpirationDate()) 
                .sign(algorithm);
            return token;
        } catch (JWTCreationException exception) {
            throw new RuntimeException("Error generating JWT token", exception);  
        }
    }

    public String gerarTokenEscola(LoginEscola escolaLogin) {
        try {
            Algorithm algorithm = Algorithm.HMAC256(secret);

            String token = JWT.create()
                .withIssuer("gesco")
                .withSubject(escolaLogin.getEmail())
                .withClaim("roles", escolaLogin.getAuthorities().stream()
                    .map(GrantedAuthority::getAuthority)
                    .collect(Collectors.toList()))  
                .withExpiresAt(generateExpirationDate()) 
                .sign(algorithm);
            return token;
        } catch (JWTCreationException exception) {
            throw new RuntimeException("Error generating JWT token", exception);  
        }
    }

    public String validarToken(String token) {
        try {
            Algorithm algorithm = Algorithm.HMAC256(secret);
            return JWT.require(algorithm)
                .withIssuer("gesco")
                .build()
                .verify(token)
                .getSubject();
        } catch (JWTVerificationException e) {
            throw new RuntimeException("Error validating JWT token", e);  
        }
    }

    private Date generateExpirationDate() {
        LocalDateTime expirationTime = LocalDateTime.now().plusHours(24);
        return Date.from(expirationTime.toInstant(ZoneOffset.UTC));
    }    
}
