package com.backend.ecommercebackend.service.impl;

import com.backend.ecommercebackend.dto.ProductDto;
import com.backend.ecommercebackend.service.ProductService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductServiceImpl implements ProductService {

    @Override
    public ProductDto addProduct(ProductDto request) {
        return null;
    }

    @Override
    public List<ProductDto> getAll() {
        return List.of();
    }

    @Override
    public ProductDto updateProduct(Long id, ProductDto request) {
        return null;
    }

    @Override
    public void deleteProduct(Long id) {

    }
}
