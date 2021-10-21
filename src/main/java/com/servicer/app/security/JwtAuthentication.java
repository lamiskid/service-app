package com.servicer.app.security;

import com.servicer.app.user.UserCustomDetails;
import io.jsonwebtoken.*;
import io.jsonwebtoken.security.InvalidKeyException;
import io.jsonwebtoken.security.Keys;
import io.jsonwebtoken.security.SignatureException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.time.LocalDate;

import static io.jsonwebtoken.Jwts.parser;
import static java.util.Date.from;


@Service
public class JwtAuthentication {


    String key = "securesecuresecuresecuresecuresecuresecuresecuresecuresecuresecure";

    ///////
    public String generateToken(Authentication authentication) throws InvalidKeyException {

        String key = "securesecuresecuresecuresecuresecuresecuresecuresecuresecuresecure";

        UserDetails principal = (UserDetails) authentication.getPrincipal();
        return Jwts.builder().setSubject(principal.getUsername()).
                setIssuedAt(from(Instant.now()))

                .claim("Authorities", authentication.getAuthorities())
                .signWith(Keys.hmacShaKeyFor(key.getBytes()))
                .setExpiration(java.sql.Date.valueOf(LocalDate.now().plusWeeks(2))).compact();

    }

    public String getUsernameFromJwt(String token) throws SignatureException, ExpiredJwtException,
            UnsupportedJwtException, MalformedJwtException, IllegalArgumentException {
        Claims claims = parser().setSigningKey(Keys.hmacShaKeyFor(key.getBytes())).parseClaimsJws(token).getBody();
        return claims.getSubject();
    }


    public boolean validateToken(String jwt) throws SignatureException, ExpiredJwtException, UnsupportedJwtException,
            MalformedJwtException, IllegalArgumentException {
        Jwts.parser().setSigningKey(Keys.hmacShaKeyFor(key.getBytes())).parseClaimsJws(jwt);
        return true;
    }

    public Long getJwtExpirationInMillis() {
        return System.currentTimeMillis() + 900;

    }
}

