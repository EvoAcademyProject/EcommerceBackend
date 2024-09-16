package com.backend.ecommercebackend.service.impl;

import com.backend.ecommercebackend.dto.request.ChangePasswordRequest;
import com.backend.ecommercebackend.dto.request.EmailVerifyRequest;
import com.backend.ecommercebackend.model.User;
import com.backend.ecommercebackend.repository.UserRepository;
import com.backend.ecommercebackend.service.ChangePasswordService;
import com.backend.ecommercebackend.cache.service.RedisVerificationService;
import lombok.RequiredArgsConstructor;

import org.springframework.data.crossstore.ChangeSetPersister;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;


@Service
@RequiredArgsConstructor
public class ChangePasswordServiceImpl implements ChangePasswordService {
    private final PasswordEncoder passwordEncoder;
    private final RedisVerificationService redisVerificationService;
    private final UserRepository repository;

    @Override
    public String changePassword(UserDetails userDetails, ChangePasswordRequest changePasswordRequest) {
        User user =repository.findByEmail(userDetails.getUsername()).orElseThrow(() -> new UsernameNotFoundException("User Not Found!"));

        if (!passwordEncoder.matches(changePasswordRequest.getOldPassword(), user.getPassword())) {
            throw new IllegalStateException("Wrong old password");
        }

        if (!changePasswordRequest.getNewPassword().equals(changePasswordRequest.getConfirmPassword())) {
            throw new IllegalStateException("Passwords do not match");
        }

        if (passwordEncoder.matches(changePasswordRequest.getNewPassword(), user.getPassword())) {
            throw new IllegalStateException("New password cannot be the same as the old password");
        }
        user.setPassword(passwordEncoder.encode(changePasswordRequest.getNewPassword()));
        repository.save(user);

        return "Password changed successfully";
    }

    @Override
    public Boolean verifyEmail(@RequestBody EmailVerifyRequest emailVerifyRequest) {
       String verificationCode = redisVerificationService.getVerificationCode(emailVerifyRequest.getEmail());

        try {
            if (verificationCode == null) {
                System.out.println("VerificationCode not found");
                return false;
            }
            return emailVerifyRequest.getVerificationCode().equals(verificationCode);
        } catch (Exception e) {
            System.out.println("Error during verification: " + e.getMessage());
            return false;
        }
    }


}
