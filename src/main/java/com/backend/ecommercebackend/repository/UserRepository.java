package com.backend.ecommercebackend.repository;

import com.backend.ecommercebackend.model.Users;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UsersRepository extends JpaRepository<Users, Long> {
}
