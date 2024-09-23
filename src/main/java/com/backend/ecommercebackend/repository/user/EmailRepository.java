package com.backend.ecommercebackend.repository.user;

import com.backend.ecommercebackend.model.user.UserEmail;
import java.util.Optional;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EmailRepository extends JpaRepository<UserEmail, Long> {
  @Transactional
  void deleteByEmail(String email);
  Optional<UserEmail> findByEmail(String email);

}