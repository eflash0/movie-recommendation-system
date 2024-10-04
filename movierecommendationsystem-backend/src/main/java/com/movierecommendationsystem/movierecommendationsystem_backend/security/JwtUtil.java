package com.movierecommendationsystem.movierecommendationsystem_backend.security;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.Base64;
import java.util.Date;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import jakarta.annotation.PostConstruct;

import javax.crypto.SecretKey;


@Component
public class JwtUtil {
    @Value("${jwt.secret}")
    private String secret;
    @Value("${jwt.expiration}")
    private long jwtExpiration;

    private SecretKey secretKey;

    @PostConstruct
    public void init(){
        secretKey = Keys.hmacShaKeyFor(Base64.getDecoder().decode(secret));
    }

    public String generateToken(String username,String role){
        return Jwts.builder()
                   .setSubject(username)
                   .claim("role",role)
                   .setIssuedAt(new Date())
                   .setExpiration(new Date(new Date().getTime()+jwtExpiration*1000))
                   .signWith(secretKey,SignatureAlgorithm.HS512)
                   .compact();
    }

    private Claims getAllClaimsFromToken(String token){
        return Jwts.parserBuilder()
                   .setSigningKey(secretKey)
                   .build()
                   .parseClaimsJws(token)
                   .getBody();
    }
    public String getUsernameFromToken(String token){
        return getAllClaimsFromToken(token).getSubject();
    }

    public boolean validateToken(String token){
        try{
            Jwts.parserBuilder().setSigningKey(secretKey).build().parseClaimsJws(token);
            return true;
        }
        catch(Exception e){
            return false;
        }
    }
}
