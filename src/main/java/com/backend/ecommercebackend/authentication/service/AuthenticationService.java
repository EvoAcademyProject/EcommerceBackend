package com.backend.ecommercebackend.authentication.service;

import com.backend.ecommercebackend.authentication.model.AuthRequest;
import com.backend.ecommercebackend.authentication.model.AuthResponse;
import com.backend.ecommercebackend.authentication.model.RegisterRequest;


public interface AuthenticationService {
     AuthResponse register(RegisterRequest request);
     AuthResponse authenticate(AuthRequest request);
}
