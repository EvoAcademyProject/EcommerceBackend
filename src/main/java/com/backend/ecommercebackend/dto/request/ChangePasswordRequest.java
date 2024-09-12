package com.backend.ecommercebackend.dto.request;

import lombok.*;

@Data
@Builder
@AllArgsConstructor

public class ChangePasswordRequest {
    private String oldPassword;
    private String newPassword;
    private String confirmPassword;
}
