package com.backend.ecommercebackend.service.impl;

import com.backend.ecommercebackend.dto.request.ProductRequest;
import com.backend.ecommercebackend.dto.response.ProductResponse;
import com.backend.ecommercebackend.enums.Exceptions;
import com.backend.ecommercebackend.exception.ApplicationException;
import com.backend.ecommercebackend.mapper.ProductMapper;
import com.backend.ecommercebackend.model.product.Product;
import com.backend.ecommercebackend.repository.product.ProductRepository;
import com.backend.ecommercebackend.service.FileStorageService;
import com.backend.ecommercebackend.service.ProductService;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import java.io.IOException;
import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class ProductServiceImpl implements ProductService {
    private final ProductMapper mapper;
    private final ProductRepository repository;
    private final FileStorageService fileStorageService;

    @Override
    public ProductResponse addProduct(ProductRequest request,MultipartFile imageFile) {
        Product product = mapper.ProductDtoToEntity(request);
        if (imageFile != null && !imageFile.isEmpty()) {
            try {
                String imageUrl = fileStorageService.storeFile(imageFile);
                product.setImageUrl(imageUrl);
            } catch (IOException e) {
                throw new RuntimeException("Failed to store image", e);
            }
        }
        repository.save(product);
        return mapper.EntityToProductDto(product);
    }

    @Override
    public ProductResponse getProductById(Long id) {
       Product product= repository.findById(id).orElseThrow(()->new ApplicationException(Exceptions.NOT_FOUND_EXCEPTION));
        return mapper.EntityToProductDto(product);
    }

    @Override
    public List<ProductResponse> getAllProduct() {
        return mapper.EntityListToProductDtoList(repository.findAll());
    }

    @Override
    public ProductResponse updateProduct(Long id, ProductRequest request, MultipartFile imageFile) {
        Product product = repository.findById(id).orElseThrow(() -> new RuntimeException("Product not found with id: " + id));


        mapper.updateProductFromProductDto(request, product);

        if (imageFile != null && !imageFile.isEmpty()) {
            try {
                String imageUrl = fileStorageService.storeFile(imageFile);

                if (product.getImageUrl() != null) {
                    fileStorageService.deleteFile(product.getImageUrl());
                }
                product.setImageUrl(imageUrl);
            } catch (IOException e) {
                throw new RuntimeException("Failed to store image", e);
            }
        }
        repository.save(product);
        return mapper.EntityToProductDto(product);
    }

    @Override
    public void deleteProduct(Long id) {
        Product product = repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Product not found with id: " + id));

        if (product.getImageUrl() != null) {
            try {
                fileStorageService.deleteFile(product.getImageUrl());
            } catch (IOException e) {
                throw new RuntimeException("Failed to delete image", e);
            }
        }

        repository.deleteById(id);
    }


    @Override
    public ProductResponse rateProduct(Long productId, float rating) {
        Product product = repository.findById(productId)
                .orElseThrow(() -> new ApplicationException(Exceptions.NOT_FOUND_EXCEPTION));

        product.setRatingSum(product.getRatingSum() + rating);
        product.setTotalRatings(product.getTotalRatings() + 1);

        float newAverageRating = product.getRatingSum() / product.getTotalRatings();
        product.setRating(newAverageRating);

        repository.save(product);

        return mapper.EntityToProductDto(product);
    }

}
