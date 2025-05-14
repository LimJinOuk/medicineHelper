package com.jinouk.medicinehelper.global.jwt.jwtUtil;


import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.security.Key;
import java.util.Date;

@Component
public class jwtUtil {

    //Secret키 설정
    @Value("${jwt.secret}")
    private String secret;
    
    //만료 기간 설정
    @Value("${jwt.expiration}")
    private long expiration;

    private Key getSignKey()
    {
        return Keys.hmacShaKeyFor(secret.getBytes());
    }

    //Generate The Token
    public String generateToken(String username , String role)
    {
        Date now = new Date();
        Date expiryDate = new Date(now.getTime() + expiration);

        return Jwts.builder()
                .setSubject(username)
                .claim("role" , role)
                .setIssuedAt(now)
                .setExpiration(expiryDate)
                .signWith(getSignKey() , SignatureAlgorithm.HS256)
                .compact();
    }
}
