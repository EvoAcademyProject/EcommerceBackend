package com.backend.ecommercebackend.authentication.service.impl;

import com.backend.ecommercebackend.authentication.dto.request.LogoutRequest;
import com.backend.ecommercebackend.authentication.dto.request.RegisterRequest;
import com.backend.ecommercebackend.authentication.jwt.JwtService;
import com.backend.ecommercebackend.authentication.dto.request.AuthRequest;
import com.backend.ecommercebackend.authentication.mapper.AuthMapper;
import com.backend.ecommercebackend.authentication.model.AuthResponse;
import com.backend.ecommercebackend.authentication.service.AuthenticationService;
import com.backend.ecommercebackend.cache.service.RedisTokenService;
import com.backend.ecommercebackend.model.Role;
import com.backend.ecommercebackend.model.User;
import com.backend.ecommercebackend.repository.UserRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
public class AuthenticationServiceImpl implements AuthenticationService {

    private final UserRepository repository;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;
    private final AuthenticationManager authenticationManager;
    private final AuthMapper authMapper;
    private final UserDetailsService userDetailsService;
    private final RedisTokenService redisTokenService;

    @Override
    public AuthResponse register(RegisterRequest request) {
        User user = authMapper.RegisterDtoToEntity(request,passwordEncoder);
        user.setRole(Role.USER);
        user.setCreatedAt(LocalDateTime.now());
        user.setUpdatedAt(LocalDateTime.now());
        repository.save(user);
        String accessToken = jwtService.generateAccessToken(user.getEmail());
        String refreshToken=jwtService.generateRefreshToken(user.getEmail());

        return AuthResponse.builder()
                .accessToken(accessToken)
                .refreshToken(refreshToken)
                .build();
    }

    @Override
    public AuthResponse authenticate(AuthRequest request) {
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        request.getEmail(),
                        request.getPassword()));
        User user = repository.findByEmail(request.getEmail()).orElseThrow(()-> new UsernameNotFoundException("user not found"));
        String accessToken = jwtService.generateAccessToken(user.getEmail());
        String refreshToken=jwtService.generateRefreshToken(user.getEmail());

        return AuthResponse.builder()
                .accessToken(accessToken)
                .refreshToken(refreshToken)
                .build();
    }

    @Override
    public void logout(LogoutRequest request) {
        var token = request.getRefreshToken();
        String email = request.getEmail();
        if (redisTokenService.validateRefreshToken(email, token)) {
            redisTokenService.deleteRefreshToken(email);
        } else {
            throw new IllegalArgumentException("Invalid refresh token");
        }
    }
    @Override
    public void refreshAuthToken(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String userEmail;
        String refreshToken;
        AuthResponse authResponse;
        String authHeader = request.getHeader("Authorization");
        if(authHeader==null || !authHeader.startsWith("Bearer ")){
            return ;
        }
        refreshToken = authHeader.substring(7);
        userEmail=jwtService.extractUsername(refreshToken);
        if(userEmail!=null){
            var user =this.userDetailsService.loadUserByUsername(userEmail);
            if(jwtService.validateToken(user,refreshToken)){
                String accessToken = jwtService.generateAccessToken(userEmail);
                authResponse=AuthResponse.builder()
                        .refreshToken(refreshToken)
                        .accessToken(accessToken).build();
                new ObjectMapper().writeValue(response.getOutputStream(),authResponse);
            }
        }
    }
}
