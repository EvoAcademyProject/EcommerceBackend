package com.backend.ecommercebackend.controller;

import com.backend.ecommercebackend.dto.request.ProductRequest;
import com.backend.ecommercebackend.dto.response.ProductResponse;
import com.backend.ecommercebackend.service.ProductService;

import java.util.List;

import lombok.RequiredArgsConstructor;
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
@RequestMapping("/api/v1/product/")
@RequiredArgsConstructor
public class ProductController {

  private final ProductService service;

  @GetMapping("/getAllProducts")
  public ResponseEntity<List<ProductResponse>> getAllProducts(){
    return ResponseEntity.ok(service.getAllProduct());
  }

  @GetMapping("/{id}")
  public ResponseEntity<ProductResponse> getProductById(@PathVariable Long id){
    return ResponseEntity.ok(service.getProductById(id));
  }

  @PostMapping
  public ResponseEntity<ProductResponse> createProduct(@RequestBody ProductRequest request ){
    return ResponseEntity.ok(service.addProduct(request));
  }

  @PutMapping("/{id}")
  public ResponseEntity<ProductResponse> updateProduct(@PathVariable Long id, @RequestBody ProductRequest request) {
    return ResponseEntity.ok(service.updateProduct(id, request));
  }

  @DeleteMapping("/{id}")
  public ResponseEntity<String> deleteProduct(@PathVariable Long id) {
    service.deleteProduct(id);
    return ResponseEntity.ok("Product deleted successfully");

  }
}
