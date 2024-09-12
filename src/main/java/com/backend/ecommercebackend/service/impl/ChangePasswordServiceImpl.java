package com.backend.ecommercebackend.service.impl;

import com.backend.ecommercebackend.dto.request.ChangePasswordRequest;
import com.backend.ecommercebackend.dto.request.EmailVerifyRequest;
import com.backend.ecommercebackend.service.ChangePasswordService;
import com.backend.ecommercebackend.cache.service.RedisVerificationService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;


@Service
@RequiredArgsConstructor
public class ChangePasswordServiceImpl implements ChangePasswordService {
    private final PasswordEncoder passwordEncoder;
    private final RedisVerificationService redisVerificationService;

    @Override
    public String changePassword(UserDetails userDetails, ChangePasswordRequest changePasswordRequest) {
        return "";
    }

    @Override
    public Boolean verifyEmail(@RequestBody EmailVerifyRequest emailVerifyRequest) {
       String verificationCode = redisVerificationService.getVerificationCode(emailVerifyRequest.getEmail());
        if(verificationCode!=null){
           return verificationCode.equals(emailVerifyRequest.getVerificationCode());
        }
        return false;
    }


}
