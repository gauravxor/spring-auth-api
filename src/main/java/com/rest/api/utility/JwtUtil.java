package com.rest.api.utility;
import com.rest.api.models.User;
import io.jsonwebtoken.Jwts;
import java.util.Date;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import java.security.Key;

public class JwtUtil {
    private String AccessToken;
    private String RefreshToken;
    private static final Key SECRET_KEY = Keys.secretKeyFor(SignatureAlgorithm.HS256);


    public String generateAccessToken(User userData) {
        System.out.println("Generating the access tokens");
        long expiryTime = System.currentTimeMillis() + 1000 * 60 * 60; // 1 hour

        String accessToken = Jwts.builder()
                .setSubject(userData.getUserId())  // owner of token
                .claim("name", userData.getName())
                .claim("emailId", userData.getEmailId())
                .setIssuedAt(new Date())
                .setExpiration(new Date(expiryTime))
                .signWith(SECRET_KEY)
                .compact();
        return accessToken;
    }
}