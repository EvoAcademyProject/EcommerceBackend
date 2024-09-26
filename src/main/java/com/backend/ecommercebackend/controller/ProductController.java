package com.backend.ecommercebackend.controller;

import com.backend.ecommercebackend.dto.request.ProductRequest;
import com.backend.ecommercebackend.dto.response.ProductResponse;
import com.backend.ecommercebackend.service.ProductService;

import java.util.List;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

@RestController
@RequestMapping("/api/v1/product/")
@RequiredArgsConstructor
public class ProductController {

  private final ProductService service;

  @GetMapping("/getAllProducts")
  public ResponseEntity<List<ProductResponse>> getAllProducts(){
    return ResponseEntity.ok(service.getAllProduct());
  }

//  @GetMapping("/getProducts/{categoryName}")
//  public ResponseEntity<List<ProductResponse>> getProductByCategoryName(@PathVariable String categoryName){
//    return ResponseEntity.ok(service.getProductByCategoryName(categoryName));
//  }

  @GetMapping("/{id}")
  public ResponseEntity<ProductResponse> getProductById(@PathVariable Long id){
    return ResponseEntity.ok(service.getProductById(id));
  }

  @PostMapping
  public ResponseEntity<ProductResponse> createProduct(@RequestBody ProductRequest request, @RequestParam(value="imageFile") MultipartFile imageFile){
     return ResponseEntity.ok(service.addProduct(request, imageFile));
  }

  @PutMapping("/{id}")
  public ResponseEntity<ProductResponse> updateProduct(@PathVariable Long id, @RequestBody ProductRequest request, @RequestParam(value="imageFile") MultipartFile imageFile) {
    return ResponseEntity.ok(service.updateProduct(id, request, imageFile));
  }

  @DeleteMapping("/{id}")
  public ResponseEntity<Void> deleteProduct(@PathVariable Long id) {
    service.deleteProduct(id);
    return ResponseEntity.noContent().build();

  }
}
