package com.backend.ecommercebackend.model;


import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.math.BigDecimal;

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

    @Column(nullable = false)
    String name;

    @Column(nullable = false)
    String modelNumber;

    @Column(nullable = false)
    String description;

    @Column(nullable = false)
    BigDecimal price;

    @Column(nullable = false)
    double rating;

    @Column(nullable = false)
    String imageUrl;

    @ManyToOne
    @JoinColumn(name = "category_id")
    Category category;
}
