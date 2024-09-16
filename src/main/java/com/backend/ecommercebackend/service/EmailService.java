package com.backend.ecommercebackend.service;

import com.backend.ecommercebackend.dto.request.EmailRequest;

public interface EmailService {
    void sendEmail(String email);
    String createVerificationCode();
    void storeEmail(EmailRequest request);
    void deleteStoredEmail(String email);
}
