package com.backend.ecommercebackend.authentication.service;

import com.backend.ecommercebackend.authentication.model.CustomUserDetails;
import com.backend.ecommercebackend.model.User;
import com.backend.ecommercebackend.repository.user.UserRepository;
import jakarta.persistence.EntityManagerFactory;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class CustomUserDetailsService implements UserDetailsService {
    private final EntityManagerFactory entityManagerFactory;
    private final UserRepository repository;

    public CustomUserDetailsService(@Qualifier("firstEntityManagerFactory") EntityManagerFactory entityManagerFactory, UserRepository repository) {
        this.entityManagerFactory = entityManagerFactory;
        this.repository = repository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = repository.findByEmail(username).orElseThrow(() -> new UsernameNotFoundException("user not found"));
        return new CustomUserDetails(user);
    }
}
