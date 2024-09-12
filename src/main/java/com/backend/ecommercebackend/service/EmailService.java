package com.backend.ecommercebackend.service;

public interface EmailService {
    void sendEmail(String email);
    String createVerificationCode();
}
