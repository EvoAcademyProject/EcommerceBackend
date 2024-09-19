package com.backend.ecommercebackend.controller;

import com.backend.ecommercebackend.dto.request.EmailRequest;
import com.backend.ecommercebackend.service.impl.EmailServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/v1/auth/")
@RequiredArgsConstructor
@Validated
public class EmailController {
    private final EmailServiceImpl emailService;

    @PostMapping("/sendVerificationCode")
    public void sendEmail(@RequestParam("email") String email) {
        emailService.sendVerificationCode(email);
    }

    @PostMapping("/register-email")
    public ResponseEntity<String> storeEmail(@Valid @RequestBody EmailRequest request) {
        emailService.storeEmail(request);
        return ResponseEntity.ok("User email stored successfully");
    }
}
