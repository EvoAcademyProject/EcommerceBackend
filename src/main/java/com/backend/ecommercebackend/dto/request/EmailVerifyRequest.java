package com.backend.ecommercebackend.dto.request;


import lombok.Getter;
import lombok.Setter;

@Getter
public class EmailVerifyRequest {
    private String email;
    private String verificationCode;
}
