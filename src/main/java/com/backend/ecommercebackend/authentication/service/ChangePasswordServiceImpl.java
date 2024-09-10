package com.backend.ecommercebackend.authentication.service;

import com.backend.ecommercebackend.authentication.model.ChangePasswordRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;


@Service
@RequiredArgsConstructor
public class ChangePasswordServiceImpl implements ChangePasswordService{
    private final PasswordEncoder passwordEncoder;

    @Override
    public String changePassword(UserDetails userDetails, ChangePasswordRequest changePasswordRequest) {
        return "";
    }


}
