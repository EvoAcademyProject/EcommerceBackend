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
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class ProductServiceImpl implements ProductService {
    private final ProductMapper mapper;
    private final ProductRepository repository;
    private final FileStorageService fileStorageService;

    @Override
    public ProductResponse addProduct(ProductRequest request, List<MultipartFile> imageFiles) {
        if (request.getSpecifications() != null) {
            try {
                ObjectMapper objectMapper = new ObjectMapper();
                List<Object> specifications = objectMapper.readValue(request.getSpecifications().toString(), new TypeReference<>() {
                });
                request.setSpecifications(specifications);
            } catch (JsonProcessingException e) {
                throw new ApplicationException(Exceptions.INVALID_FORMAT_EXCEPTION);
            }
        }
        Product product = mapper.ProductDtoToEntity(request);
        List<String> imageUrls = new ArrayList<>();
        for (MultipartFile imageFile : imageFiles) {
            if (imageFile != null && !imageFile.isEmpty()) {
                try {
                    String fileName = fileStorageService.storeFile(imageFile);
                    String imageUrl = "https://ff82f4df-f72b-4dec-84ca-487132aff620.mock.pstmn.io/uploads/" + fileName;
                    imageUrls.add(imageUrl);
                    product.setImageUrl(imageUrls);
                } catch (IOException e) {
                    throw new ApplicationException(Exceptions.IMAGE_STORAGE_EXCEPTION);
                }
            }
        }

        repository.save(product);
        return mapper.EntityToProductDto(product);
    }

    @Override
    public ProductResponse updateProduct(Long id, ProductRequest request, List<MultipartFile> imageFiles) {
        Product product = repository.findById(id).orElseThrow(() -> new ApplicationException(Exceptions.NOT_FOUND_EXCEPTION));
        mapper.updateProductFromProductDto(request, product);
        List<String> imageUrls = new ArrayList<>();
        for (MultipartFile imageFile : imageFiles) {
            if (imageFile != null && !imageFile.isEmpty()) {
                try {
                    String imageUrl1 = fileStorageService.storeFile(imageFile);
                    imageUrls.add(imageUrl1);
                    for (String imageUrl : product.getImageUrl()) {
                        if (product.getImageUrl() != null) {
                            fileStorageService.deleteFile(imageUrl);
                        }
                        product.setImageUrl(imageUrls);
                    }
                } catch (IOException e) {
                    throw new ApplicationException(Exceptions.IMAGE_STORAGE_EXCEPTION);
                }
            }
        }
        repository.save(product);
        return mapper.EntityToProductDto(product);
    }


    @Override
    public ProductResponse getProductById(Long id) {
        Product product = repository.findById(id).orElseThrow(() -> new ApplicationException(Exceptions.NOT_FOUND_EXCEPTION));
        return mapper.EntityToProductDto(product);
    }

    @Override
    public List<ProductResponse> getAllProduct() {
        return mapper.EntityListToProductDtoList(repository.findAll());
    }


    @Override
    public void deleteProduct(Long id) {
        Product product = repository.findById(id).orElseThrow(() -> new ApplicationException(Exceptions.NOT_FOUND_EXCEPTION));
        for (String imageUrl : product.getImageUrl()) {
            if (imageUrl != null) {
                try {
                    fileStorageService.deleteFile(imageUrl);
                } catch (IOException e) {
                    log.error("Failed to delete image");
                }
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
