package com.sumerge.moviesservice.security;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import io.jsonwebtoken.security.SignatureException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.security.Key;

@Service
public class JwtValidatorServiceImpl implements JwtValidatorService {

    @Value("${jwt.secretKeyEncoded}")
    private String secret_key;


    private Key getSigningKey() {
        byte[] keyBytes = Decoders.BASE64.decode(secret_key);
        return Keys.hmacShaKeyFor(keyBytes);
    }

    @Override
    public void validateToken(String authHeader) {

        if(authHeader == null || !authHeader.startsWith("Bearer ")) {
            throw new SignatureException("no Jwt token were found!") ;
        }
        String token = authHeader.substring(7);

            Jwts.parserBuilder()
                    .setSigningKey(getSigningKey())
                    .build()
                    .parseClaimsJws(token);

    }

    // in case of a valid token no exceptions to be thrown
    // otherwise, three types of exceptions expected:
    // SignatureException | MalformedJwtException | ExpiredJwtException



}
