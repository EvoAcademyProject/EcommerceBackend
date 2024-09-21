package com.backend.ecommercebackend.model;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;

import com.backend.ecommercebackend.enums.CategoryType;
import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.JdbcTypeCode;
import org.hibernate.annotations.UpdateTimestamp;
import org.hibernate.type.SqlTypes;
import org.springframework.format.annotation.DateTimeFormat;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "product-specification")
@FieldDefaults(level = AccessLevel.PRIVATE)
public class ProductSpecification {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  Long id;

  @Column(nullable = false)
  String specificationName;

  @ManyToOne
  @JoinColumn(name = "product_id")
  Product product;

  @CreationTimestamp
  @Column(name = "created_at", nullable = false, updatable = false)
  @DateTimeFormat(pattern = "yyyy-MM-dd")
  LocalDateTime createdAt;

  @UpdateTimestamp
  @Column(name = "updated_at", nullable = false)
  @DateTimeFormat(pattern = "yyyy-MM-dd")
  LocalDateTime updatedAt;
}
