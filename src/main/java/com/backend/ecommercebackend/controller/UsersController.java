package com.backend.ecommercebackend.controller;

import com.backend.ecommercebackend.dto.UsersDto;
import com.backend.ecommercebackend.service.impl.UsersServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RequestMapping("/api/v1/user/")
@RestController
@RequiredArgsConstructor
public class UsersController {
    private final UsersServiceImpl service;
    @GetMapping("/allUsers")
    public List<UsersDto> getAllUsers() {
        return service.getAll();
    }
}
