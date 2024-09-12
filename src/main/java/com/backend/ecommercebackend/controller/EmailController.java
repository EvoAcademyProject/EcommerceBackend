package com.backend.ecommercebackend.controller;

import com.backend.ecommercebackend.service.impl.EmailServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/auth/")
@RequiredArgsConstructor
public class EmailController {
    private final EmailServiceImpl emailService;
    @PostMapping("/sendEmail")
    public void sendEmail(@RequestParam("email") String email) {
        emailService.sendEmail(email);
    }

}
