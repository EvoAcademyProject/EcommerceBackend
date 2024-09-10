package com.backend.ecommercebackend.authentication.service;

import io.jsonwebtoken.Claims;

public interface TokenService {

    String generateRefreshToken(String username);
    Claims validateToken(String token);
}
