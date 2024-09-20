package com.backend.ecommercebackend.model;


import com.backend.ecommercebackend.enums.CategoryType;
import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;
import org.hibernate.annotations.JdbcTypeCode;
import org.hibernate.type.SqlTypes;

import java.math.BigDecimal;
import java.util.List;

@Entity
@Data
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
    @Lob
    String description;

    @Column(nullable = false)
    int price;

    @Column(nullable = false)
    float rating;

    @Column(nullable = false)
    String imageUrl;

    @JoinColumn(nullable = false)
    @Enumerated(EnumType.STRING)
    CategoryType category;

    @JdbcTypeCode(SqlTypes.JSON)
    @Column(columnDefinition = "jsonb", nullable = false)
    @OneToMany(mappedBy = "product",cascade = CascadeType.ALL)
    List<ProductSpecification> specificationName;
}
