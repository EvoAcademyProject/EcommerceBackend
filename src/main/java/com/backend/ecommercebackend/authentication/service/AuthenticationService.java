package com.backend.ecommercebackend.authentication.service;

import com.backend.ecommercebackend.authentication.dto.request.AuthRequest;
import com.backend.ecommercebackend.authentication.model.AuthResponse;
import com.backend.ecommercebackend.authentication.dto.request.RegisterRequest;


public interface AuthenticationService {
     AuthResponse register(RegisterRequest request);
     AuthResponse authenticate(AuthRequest request);
}
