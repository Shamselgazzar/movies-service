package com.sumerge.moviesservice.security;

import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import io.jsonwebtoken.security.SignatureException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.MockitoAnnotations;
import org.springframework.test.util.ReflectionTestUtils;

import java.security.Key;
import java.util.Date;

import static org.junit.jupiter.api.Assertions.assertThrows;
class JwtValidatorServiceImplTest {

    private JwtValidatorServiceImpl jwtValidatorService;

    private final String secret_key = "zhQtXw29SYwaXRnICaoEBdhttcEzEtN90IvhHTbb02hDluGWI7jeSXyPRxXjqGs1Nb9tSagqRu3LYV4BKkNXVA==";
    @BeforeEach
    public void setup() {
        MockitoAnnotations.openMocks(this);
        jwtValidatorService = new JwtValidatorServiceImpl();
        ReflectionTestUtils.setField(jwtValidatorService,"secret_key", secret_key);
    }
    @Test
    public void testValidateToken_WithValidToken() {
        String validToken = createValidToken();

        //no exception thrown
        jwtValidatorService.validateToken("Bearer " + validToken);
    }

    @Test
    public void testValidateToken_WithInvalidToken() {
        String invalidToken = "invalidTokenHere";

        // JwtException should be thrown for an invalid token
        assertThrows(JwtException.class, () -> jwtValidatorService.validateToken("Bearer " + invalidToken));
    }

    @Test
    public void testValidateTokenWithNoToken() {
        // throw SignatureException
        assertThrows(SignatureException.class, () -> jwtValidatorService.validateToken(null));
    }

// creating a valid token to test with
    private String createValidToken(){
        long expirationPeriod = 86400000;
        Date now = new Date(System.currentTimeMillis());
        Date expirationDate = new Date(now.getTime() + expirationPeriod);
        return Jwts.builder()
                .setSubject("email")
                .setIssuedAt(now)
                .setExpiration(expirationDate)
                .signWith(getTestSigningKey(), SignatureAlgorithm.HS512)
                .compact();
    }

    private Key getTestSigningKey() {
        byte[] keyBytes = Decoders.BASE64.decode(secret_key);
        return Keys.hmacShaKeyFor(keyBytes);
    }


}