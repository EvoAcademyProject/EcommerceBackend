package com.backend.ecommercebackend.service;
import com.backend.ecommercebackend.dto.request.ProductRequest;
import com.backend.ecommercebackend.dto.response.ProductResponse;

import java.util.List;

public interface ProductService {
     ProductResponse addProduct(ProductRequest request);
     ProductResponse getProductById(Long id);
     List<ProductResponse> getAllProduct();
     ProductResponse updateProduct(Long id, ProductRequest request);
     void deleteProduct(Long id);
     ProductResponse rateProduct(Long id, float newRating);
}
