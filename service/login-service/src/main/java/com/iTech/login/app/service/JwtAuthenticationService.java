package com.itech.login.app.service;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import org.springframework.stereotype.Service;

import java.security.Key;
import java.util.Date;
import java.util.Map;
import java.util.function.Function;

@Service
public class JwtAuthenticationService {

    private static final String SECRET = "AAAAANBUHEHIUHOEHOOBOEBOBOBDNBONMNKNLKJLKKNKNKNKNKNJBJNKNMKNKNKHHINKNOINOIEOIODBUHIUEGYFETCCDSVHEVHBHBEBBBHVDVV";

    public String generateToken(Map<String, Object> claims, String userName) {
        return Jwts.builder()
                .setClaims(claims)
                .setSubject(userName)
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis() + 30 * 60 * 1000))
                .signWith(getSigningKey(), SignatureAlgorithm.HS512)
                .compact();

    }

    public String extractUserName(String token) {
        return extractClaim(token, Claims::getSubject);
    }

    private <T> T extractClaim(String token, Function<Claims, T> claimResolver) {
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
        byte[] secret = Decoders.BASE64URL.decode(SECRET);
        return Keys.hmacShaKeyFor(secret);

    }

    public boolean isValidToken(String token) {
        Date expDate = extractClaim(token, Claims::getExpiration);
        return expDate.before(new Date(System.currentTimeMillis() + 1000 * 60));
    }
}
