package com.backend.ecommercebackend.dto.request;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.Size;

public record UsersRequest(

        @Size(min = 5, max = 10, message = "your name size is not valid")
        String name,
        @Min(value = 5, message = "your surname size under the minimum")
        @Max(value = 16, message = "your surname size over the max")
        String surname,
        String email,
        String username,
        String password,
        Integer age

) {
}
