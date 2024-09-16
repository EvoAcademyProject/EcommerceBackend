package com.backend.ecommercebackend.dto.request;

import lombok.Getter;
import lombok.Setter;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;

@Getter
@Setter
public class EmailRequest {
  @Email
  @NotEmpty
  private String email;
}