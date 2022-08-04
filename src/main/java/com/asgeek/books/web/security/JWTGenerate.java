package com.asgeek.books.web.security;

import com.asgeek.books.domain.auth.UserAuth;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;


import java.security.Key;
import java.util.Date;

@Component
public class JWTGenerate {

    private static final Key KEY = Keys.secretKeyFor(SignatureAlgorithm.HS256);

    public String generateToken(UserDetails userDetails){
        // Método de autenticación cuando se utiliza java security
        return Jwts.builder().setSubject(userDetails.getUsername())
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis() + 1000 * 60 * 60 * 10))
                .signWith(KEY)
                .compact();
    }

    public String generateTokenV2(UserAuth userDetails){
        return Jwts.builder().setSubject(userDetails.getEmail())
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis() + 1000 * 60 * 60 * 10))
                .signWith(KEY)
                .compact();
    }

    public boolean validateToken(String token, UserAuth userAuth){
        return userAuth.getEmail().equals(extractEmail(token)) && !isTokenExpired(token);
    }

    public String extractEmail(String token){
        return getClaims(token).getSubject();
    }

    public boolean isTokenExpired(String token) {
        return getClaims(token).getExpiration().before(new Date());
    }
    public Claims getClaims(String token){
        return Jwts.parserBuilder().setSigningKey(KEY)
                .build().parseClaimsJws(token).getBody();
    }

}
