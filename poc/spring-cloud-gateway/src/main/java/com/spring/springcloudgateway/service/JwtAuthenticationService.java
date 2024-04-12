package com.spring.springcloudgateway.service;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import org.springframework.stereotype.Service;

import java.security.Key;
import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.Date;
import java.util.Map;
import java.util.function.Function;

@Service
public class JwtAuthenticationService {

    private static final String SECRET = "AAAAANBUHEHIUHOEHOOBOEBOBOBDNBONOINOIEOIODBUHIUEGYFETCCDSVHEVHBHBEBBBHVDVV";

    public String generateToken(Map<String, Object> claims, String userName) {
        return Jwts.builder()
                .setClaims(claims)
                .setSubject(userName)
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis()+ 5*60*1000))
                .signWith(getSigningKey(), SignatureAlgorithm.HS512)
                .compact();

    }
    public  String extractUserName(String token){
        return extractClaim(token, Claims::getSubject);
    }

    private <T> T extractClaim(String token, Function<Claims,T> claimResolver) {
         Claims claims = extractAllClaims(token);
        return claimResolver.apply(claims);
    }

    private Claims extractAllClaims(String token) {
       return Jwts
               .parserBuilder()
               .setSigningKey(getSigningKey())
               .build()
               .parseClaimsJwt(token)
               .getBody();
    }

    private Key getSigningKey() {
        byte[] secret = Decoders.BASE64.decode(SECRET);
        return Keys.hmacShaKeyFor(secret);

    }
}
