package com.backend.ecommercebackend.authentication.service;

import com.backend.ecommercebackend.authentication.dto.request.AuthRequest;
import com.backend.ecommercebackend.authentication.dto.request.LogoutRequest;
import com.backend.ecommercebackend.authentication.model.AuthResponse;
import com.backend.ecommercebackend.authentication.dto.request.RegisterRequest;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;


public interface AuthenticationService {
     AuthResponse register(RegisterRequest request);
     AuthResponse authenticate(AuthRequest request);
     void logout(LogoutRequest request);
     void refreshAuthToken(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException;
}
