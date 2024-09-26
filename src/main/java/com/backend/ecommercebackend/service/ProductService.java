package com.backend.ecommercebackend.service;
import com.backend.ecommercebackend.dto.request.ProductRequest;
import com.backend.ecommercebackend.dto.response.ProductResponse;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface ProductService {
     ProductResponse addProduct(ProductRequest request, MultipartFile imageFile);
     ProductResponse getProductById(Long id);
     List<ProductResponse> getAllProduct();
     ProductResponse updateProduct(Long id, ProductRequest request, MultipartFile imageFile);
     void deleteProduct(Long id);
//     List<ProductResponse> getProductByCategoryName(String categoryName);
}
