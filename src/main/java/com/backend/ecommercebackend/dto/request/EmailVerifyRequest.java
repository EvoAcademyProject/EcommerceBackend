package com.backend.ecommercebackend.dto.request;


import lombok.Getter;

@Getter
public class EmailVerifyRequest {
    private String email;
    private String verificationCode;
}
