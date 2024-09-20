package com.backend.ecommercebackend.service;

import com.backend.ecommercebackend.dto.ProductDto;

import java.util.List;

public interface ProductService {
    public ProductDto addProduct(ProductDto request);
    public List<ProductDto> getAll();
    public ProductDto updateProduct(Long id, ProductDto request);
    public void deleteProduct(Long id);
}
