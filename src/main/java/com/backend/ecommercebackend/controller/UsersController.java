package com.backend.ecommercebackend.controller;

import com.backend.ecommercebackend.model.User;
import com.backend.ecommercebackend.repository.UserRepository;
import com.backend.ecommercebackend.service.TokenService;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api")
public class UsersController {

    private final TokenService tokenService;
    private final Map<String, String> refreshTokens = new HashMap<>();

    public UsersController(TokenService tokenService) {
        this.tokenService = tokenService;
    }

    @PostMapping("/login")
    public Map<String, String> login(@RequestBody Map<String, String> body) {
        String username = body.get("username");

        String accessToken = tokenService.generateAccessToken(username);
        String refreshToken = tokenService.generateRefreshToken(username);
        refreshTokens.put(username, refreshToken); // Store refresh token

        Map<String, String> tokens = new HashMap<>();
        tokens.put("accessToken", accessToken);
        tokens.put("refreshToken", refreshToken);
        return tokens;
    }

    @PostMapping("/token")
    public Map<String, String> refreshToken(@RequestBody Map<String, String> body) {
        String username = body.get("username");
        String token = body.get("refreshToken");
        String storedRefreshToken = refreshTokens.get(username);

        if (storedRefreshToken == null || !storedRefreshToken.equals(token)) {
            throw new RuntimeException("Refresh token is invalid");
        }

        String newAccessToken = tokenService.generateAccessToken(username);
        return Map.of("accessToken", newAccessToken);
    }

    @PostMapping("/logout")
    public void logout(@RequestBody Map<String, String> body) {
        String username = body.get("username");
        refreshTokens.remove(username);
    }
}
