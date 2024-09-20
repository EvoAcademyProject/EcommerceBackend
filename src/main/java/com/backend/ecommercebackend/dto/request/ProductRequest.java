package com.backend.ecommercebackend.dto.request;

import com.backend.ecommercebackend.enums.CategoryType;
import jakarta.persistence.Column;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.JdbcTypeCode;
import org.hibernate.type.SqlTypes;

import java.util.List;

@Getter
@Setter
public class ProductRequest {
    String name;
    String modelNumber;
    int price;
    float rating;
    String imageUrl;
    @Enumerated(EnumType.STRING)
    CategoryType categoryType;
    @JdbcTypeCode(SqlTypes.JSON)
    @Column(columnDefinition = "jsonb", nullable = false)
    List<Object> specifications;
}
