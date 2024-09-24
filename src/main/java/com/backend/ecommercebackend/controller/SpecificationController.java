package com.backend.ecommercebackend.controller;
import com.backend.ecommercebackend.dto.request.ProductSpecificationRequest;
import com.backend.ecommercebackend.dto.response.ProductSpecificationResponse;
import com.backend.ecommercebackend.service.SpecificationService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/v1/product")
@RequiredArgsConstructor
public class SpecificationController {
    private final SpecificationService service;

    @GetMapping("/getAllSpec")
    public ResponseEntity<List<ProductSpecificationResponse>> getAllSpecifications() {
        return ResponseEntity.ok(service.getAllSpecifications());
    }

    @PostMapping("/createSpec")
    public ResponseEntity<ProductSpecificationResponse> createSpecification(@RequestBody ProductSpecificationRequest specificationRequest) {
        return ResponseEntity.ok(service.addSpecification(specificationRequest));
    }

    @PutMapping("/specUpdate/{specificationId}")
    public ResponseEntity<ProductSpecificationResponse> updateCategory(@PathVariable Long specificationId, @RequestBody ProductSpecificationRequest specificationRequest) {
        return  ResponseEntity.ok(service.updateSpecification(specificationId, specificationRequest));
    }

    @DeleteMapping("/specDelete/{categoryId}/{specificationId}")
    public void deleteCategory(@PathVariable Long specificationId,@PathVariable int categoryId) {
        service.deleteSpecification(specificationId,categoryId);
    }
}
