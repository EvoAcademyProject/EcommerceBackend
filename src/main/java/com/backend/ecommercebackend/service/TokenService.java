package com.backend.ecommercebackend.service;

import io.jsonwebtoken.Claims;

public interface TokenService {
    String generateAccessToken(String username);
    String generateRefreshToken(String username);
    Claims validateToken(String token);
}
