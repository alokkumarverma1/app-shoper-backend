package com.example.app_shoper_backend.security;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

import java.util.Date;

@Component
public class JwtUtil {

    private final String key = "mySuperSecretKeyForJWTToken1234567890";
  @Bean
    public String generateToken(String username) {

        return Jwts.builder()
                .setSubject(username)
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis() + 3 * 60 * 60 * 1000)) // 3 hours
                .signWith(SignatureAlgorithm.HS256, key)
                .compact();
    }
}
