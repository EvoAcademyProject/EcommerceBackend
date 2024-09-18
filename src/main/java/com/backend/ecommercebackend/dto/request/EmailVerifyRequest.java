package com.backend.ecommercebackend.dto.request;


import lombok.Getter;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;

@Getter
public class EmailVerifyRequest {

    @NotBlank(message = "Email is required")
    @Email(message = "Email must be valid")
    @Pattern(
            regexp = "^[A-Za-z0-9._%+-]+@(gmail\\.com|yahoo\\.com|email\\.ru|.*\\.edu)$",
            message = "Email must be a valid Gmail, Yahoo, email.ru, or educational domain"
    )
    private String email;
    private String verificationCode;
}
