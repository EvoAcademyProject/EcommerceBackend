package com.backend.ecommercebackend.authentication.service;

import com.backend.ecommercebackend.authentication.model.ChangePasswordRequest;
import org.springframework.security.core.userdetails.UserDetails;

public interface ChangePasswordService {
    String changePassword(UserDetails userDetails, ChangePasswordRequest changePasswordRequest);
}
