package com.backend.ecommercebackend.controller;

import com.backend.ecommercebackend.dto.ProductDto;
import com.backend.ecommercebackend.service.ProductService;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/products")
@RequiredArgsConstructor
public class ProductController {

  private final ProductService service;
  @PostMapping
  public ResponseEntity<ProductDto> createProduct( @RequestBody ProductDto request ){
    return ResponseEntity.ok(service.addProduct(request));
  }

  @GetMapping("/getAllProducts")
  public ResponseEntity<List<ProductDto>> getAllProducts(){
    return ResponseEntity.ok(service.getAll());
  }

  @PutMapping("/{id}")
  public ResponseEntity<ProductDto> updateProduct(@PathVariable Long id, @RequestBody ProductDto request) {
    return ResponseEntity.ok(service.updateProduct(id, request));
  }

  @DeleteMapping("/{id}")
  public ResponseEntity<String> deleteProduct(@PathVariable Long id) {
    service.deleteProduct(id);
    return ResponseEntity.ok("Product deleted successfully");

  }
}
