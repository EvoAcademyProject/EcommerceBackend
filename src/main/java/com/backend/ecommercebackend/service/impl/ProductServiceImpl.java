package com.backend.ecommercebackend.service.impl;

import com.backend.ecommercebackend.dto.request.ProductRequest;
import com.backend.ecommercebackend.dto.response.ProductResponse;
import com.backend.ecommercebackend.enums.Exceptions;
import com.backend.ecommercebackend.exception.ApplicationException;
import com.backend.ecommercebackend.mapper.ProductMapper;
import com.backend.ecommercebackend.model.product.Product;
import com.backend.ecommercebackend.repository.product.ProductRepository;
import com.backend.ecommercebackend.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ProductServiceImpl implements ProductService {
    private final ProductMapper mapper;
    private final ProductRepository repository;

    @Override
    public ProductResponse addProduct(ProductRequest request) {
        Product product = mapper.ProductDtoToEntity(request);
        repository.save(product);
        return mapper.EntityToProductDto(product);
    }

    @Override
    public ProductResponse getProductById(Long id) {
       Product product=
               repository.findById(id).orElseThrow(()->new ApplicationException(Exceptions.NOT_FOUND_EXCEPTION));
        return mapper.EntityToProductDto(product);
    }

    @Override
    public List<ProductResponse> getAllProduct() {
        return mapper.EntityListToProductDtoList(repository.findAll());
    }

    @Override
    public ProductResponse updateProduct(Long id, ProductRequest request) {
        Product product = repository.findById(id).orElseThrow(() -> new ApplicationException(Exceptions.NOT_FOUND_EXCEPTION));
        Product save = mapper.updateProductFromProductDto(request,product);
        repository.save(save);
        return mapper.EntityToProductDto(save);
    }

    @Override
    public void deleteProduct(Long id) {
      repository.deleteById(id);
    }
}
