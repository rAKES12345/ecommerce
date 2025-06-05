package com.ecommerce.ecommerce.components;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;

import java.security.Key;
import java.util.Date;

import org.springframework.stereotype.Component;

import com.ecommerce.ecommerce.entities.Admin;
import com.ecommerce.ecommerce.entities.User;

@Component
public class JwtUtil {

    private static final String SECRET_KEY = "your_super_secret_key_that_is_at_least_256_bits_long";

    private final Key key = Keys.hmacShaKeyFor(SECRET_KEY.getBytes());

    public String generateToken(User user) {
        return Jwts.builder()
                .setSubject(user.getName())
                .claim("role", user.getRole())
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis() + 86400000)) // 1 day
                .signWith(key)
                .compact();
    }
    public String generateTokenForAdmin(Admin admin) {
        return Jwts.builder()
                .setSubject(admin.getName())
                .claim("role", admin.getRole())
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis() + 86400000))
                .signWith(key)
                .compact();
    }
}
