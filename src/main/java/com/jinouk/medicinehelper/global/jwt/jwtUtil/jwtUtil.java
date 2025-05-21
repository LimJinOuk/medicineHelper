package com.jinouk.medicinehelper.global.jwt.jwtUtil;


import io.jsonwebtoken.*;
import io.jsonwebtoken.security.Keys;
import jakarta.annotation.PostConstruct;
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

    @Value("${jwt.refresh}")
    private long refresh;

    private Key key;

    @PostConstruct
    public void init() {
        this.key = Keys.hmacShaKeyFor(secret.getBytes());
    }

    private Key getSignKey() {
        return key;
    }

    //Generate The Token
    public String generateToken(String username)
    {
        Date now = new Date();
        Date expiryDate = new Date(now.getTime() + expiration);

        return Jwts.builder()
                .setSubject(username)
                .setIssuedAt(now)
                .setExpiration(expiryDate)
                .signWith(getSignKey() , SignatureAlgorithm.HS256)
                .compact();
    }
    //Generate Refresh Token
    public String generateRefresh(String username)
    {
        Date now = new Date();
        Date expiryDate = new Date(now.getTime() + refresh);

        return Jwts.builder()
                .setSubject(username)
                .setIssuedAt(now)
                .setExpiration(expiryDate)
                .signWith(getSignKey() , SignatureAlgorithm.HS256)
                .compact();

    }

    public boolean validateToken(String token)
    {
        try {
            Jws<Claims> claimsJws =
                    Jwts.parserBuilder()
                    .setSigningKey(getSignKey())
                    .build()
                    .parseClaimsJws(token);
            Date expiryDate = claimsJws.getBody().getExpiration();
            return expiryDate.before(new Date());
        }
        catch (JwtException | IllegalArgumentException e) {return false;}

   }

   public boolean validateRefresh(String RefreshToken)
   {
       try
       {
           Jws<Claims> claimsJws =
                   Jwts.parserBuilder()
                   .setSigningKey(getSignKey())
                   .build()
                   .parseClaimsJws(RefreshToken);
           Date expiryDate = claimsJws.getBody().getExpiration();
           return expiryDate.before(new Date());
       }
       catch (JwtException | IllegalArgumentException e)
       {
           return false;
       }
   }

    public String getUserName(String token)
    {
        return  Jwts.parserBuilder()
                .setSigningKey(getSignKey())
                .build()
                .parseClaimsJws(token)
                .getBody().getSubject();
    }
}
