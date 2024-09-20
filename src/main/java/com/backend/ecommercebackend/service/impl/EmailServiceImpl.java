package com.backend.ecommercebackend.service.impl;

import com.backend.ecommercebackend.dto.request.EmailRequest;
import com.backend.ecommercebackend.enums.Exceptions;
import com.backend.ecommercebackend.exception.ApplicationException;
import com.backend.ecommercebackend.model.User;
import com.backend.ecommercebackend.model.UserEmail;
import com.backend.ecommercebackend.repository.user.EmailRepository;
import com.backend.ecommercebackend.repository.user.UserRepository;
import com.backend.ecommercebackend.service.EmailService;
import com.backend.ecommercebackend.cache.service.RedisVerificationService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Random;

@Service
@RequiredArgsConstructor
public class EmailServiceImpl implements EmailService {
    @Value("${spring.mail.username}")
    private String from;
    private final JavaMailSender mailSender;
    private final RedisVerificationService redisVerificationService;
    private final EmailRepository emailRepository;
    private final UserRepository userRepository;

    @Override
    public void sendVerificationCode(String email) {
        User userEmail=userRepository.findByEmail(email)
                .orElseThrow(()->new ApplicationException(Exceptions.USER_NOT_FOUND));
        String verificationCode = createVerificationCode();
        String subject = "Şifrə dəyişikliyini təsdiqləmə kodunuz";
        String text = "təsdiqləmə kodu: " + verificationCode;
        SimpleMailMessage message = new SimpleMailMessage();
        message.setFrom(from);
        message.setSubject(subject);
        message.setText(text);
        message.setTo(userEmail.getEmail());
        mailSender.send(message);
        redisVerificationService.storeEmailVerificationCode(email,verificationCode);
    }

    @Override
    public String createVerificationCode() {
        Random random = new Random();
        int verificationCode = 1000 + random.nextInt(9000);
        return String.valueOf(verificationCode);
    }

    @Override
    public void storeEmail(EmailRequest request) {
        try {
            if (userRepository.findByEmail(request.getEmail()).isEmpty()) {
                UserEmail userEmail = UserEmail.builder()
                        .email(request.getEmail())
                        .createdAt(LocalDateTime.now())
                        .build();
                emailRepository.save(userEmail);
            } else {
                throw new ApplicationException(Exceptions.USER_ALREADY_EXIST);
            }
        } catch (DataIntegrityViolationException e) {
            throw new ApplicationException(Exceptions.USER_ALREADY_EXIST);
        }
    }

    @Override
    public void deleteStoredEmail(String email) {
        emailRepository.deleteByEmail(email);
    }
}
