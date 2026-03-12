package com.jdaniel.login_with_jwt_good.common.utils;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.auth0.jwt.interfaces.DecodedJWT;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class JwtService {
    @Value("${jwt.secret}")
    private String secret;
    @Value("${jwt.expirationTimeMils}")
    private int expirationTime;
    @Value("${jwt.issuer}")
    private String issuer = "login-api";

    private Algorithm getAlgorithm() {
        return Algorithm.HMAC256(secret);
    }

    public String generateToken(UserDetails userDetails) {
        String role = userDetails.getAuthorities().stream()
                .map(GrantedAuthority::getAuthority)
                .filter(a -> a.startsWith("ROLE_"))
                .findFirst()
                .orElse("");

        List<String> permissions = userDetails.getAuthorities().stream()
                .map(GrantedAuthority::getAuthority)
                .filter(a -> !a.startsWith("ROLE_"))
                .toList();

        return JWT.create()
                .withSubject(userDetails.getUsername())
                .withIssuer(issuer)
                .withClaim("role", role)
                .withClaim("permissions", permissions)
                .withIssuedAt(new Date())
                .withExpiresAt(new Date(System.currentTimeMillis() + expirationTime))
                .sign(getAlgorithm());
    }

    public boolean isTokenValid(String token) {
        try {
            verifyToken(token);
            return true;
        } catch (JWTVerificationException e) {
            return false;
        }
    }

    public String extractUsername(String token) {
        return verifyToken(token).getSubject();
    }

    public List<GrantedAuthority> extractAuthorities(String token) {
        DecodedJWT decodedJWT = verifyToken(token);
        List<GrantedAuthority> authorities = new ArrayList<>();

        String role  = decodedJWT.getClaim("role").asString();

        if (role != null && !role.isBlank()) {
            authorities.add(new SimpleGrantedAuthority(role));
        }

        List<String> permissions = decodedJWT.getClaim("permissions").asList(String.class);
        if (permissions != null) {
            permissions.forEach(permission -> authorities.add(new SimpleGrantedAuthority(permission)));
        }

        return authorities;
    }

    private DecodedJWT verifyToken(String token) {
        return JWT.require(getAlgorithm()).build().verify(token);
    }
}
