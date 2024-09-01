package com.backend.ecommercebackend.dto;


import lombok.*;
import lombok.experimental.FieldDefaults;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class UsersDto {
    Long id;
    String name;
    String surname;
    String email;
    String username;
    Integer age;
    LocalDateTime createdAt;
    LocalDateTime updatedAt;
}
