package com.backend.ecommercebackend.repository;

import com.backend.ecommercebackend.model.UserEmail;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EmailRepository extends JpaRepository<UserEmail, Long> {
  @Transactional
  void deleteByEmail(String email);
}