package com.sumerge.moviesservice.security;

public interface JwtValidatorService {
    void validateToken(String authHeader);

}
