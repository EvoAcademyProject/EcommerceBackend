package com.backend.ecommercebackend.dto;


import lombok.*;
import lombok.experimental.FieldDefaults;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class ProductDto {
    Long id;
    String name;
    String modelNumber;
    int price;
    int rating;
    String imageUrl;
    Long categoryId;
    String categoryType;
}
