package com.backend.ecommercebackend.authentication.service;

import com.backend.ecommercebackend.authentication.jwt.JwtService;
import com.backend.ecommercebackend.authentication.model.AuthRequest;
import com.backend.ecommercebackend.authentication.model.AuthResponse;
import com.backend.ecommercebackend.authentication.model.RegisterRequest;
import com.backend.ecommercebackend.model.Role;
import com.backend.ecommercebackend.model.User;
import com.backend.ecommercebackend.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
public class AuthenticationServiceImpl implements AuthenticationService {
    private final UserRepository repository;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;
    private final AuthenticationManager authenticationManager;
    private final UserDetailsService userDetailsService;


    @Override
    public AuthResponse register(RegisterRequest request) {
        User user = User.builder()
                .firstName(request.getFirstName())
                .lastName(request.getLastName())
                .email(request.getEmail())
                .password(passwordEncoder.encode(request.getPassword()))
                .role(Role.USER)
                .createdAt(LocalDateTime.now())
                .updatedAt(LocalDateTime.now())
        .build();
        repository.save(user);
        String accessToken = jwtService.generateAccessToken(user.getEmail());
        return AuthResponse.builder()
                .accessToken(accessToken)
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
        return AuthResponse.builder()
                .accessToken(accessToken)
                .build();
    }


}
