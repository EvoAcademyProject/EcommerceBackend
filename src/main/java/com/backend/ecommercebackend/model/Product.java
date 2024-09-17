package com.backend.ecommercebackend.model;


import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name="products")
@FieldDefaults(level = AccessLevel.PRIVATE)
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;
    String name;
    String modelNumber;
    String description;
    int price;
    int rating;
    String imageUrl;

    @ManyToOne
    @JoinColumn(name = "category_id")
    Category category;
}
