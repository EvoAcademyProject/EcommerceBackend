package com.backend.ecommercebackend.controller;

import com.backend.ecommercebackend.dto.request.CategoryRequest;
import com.backend.ecommercebackend.dto.response.CategoryResponse;
import com.backend.ecommercebackend.service.CategoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/product")
@RequiredArgsConstructor
public class CategoryController {
    private final CategoryService service;

    @GetMapping("/getSpec/{categoryId}")
    public ResponseEntity<List<String>> getAllSpecByCategoryId(@PathVariable int categoryId) {
        return ResponseEntity.ok(service.getSpecsByCategoryId(categoryId));
    }

    @GetMapping("/getAllCategory")
    public ResponseEntity<List<CategoryResponse>> getAllCategories() {
        return ResponseEntity.ok(service.getAllCategories());
    }

    @PostMapping("/createCategory")
    public ResponseEntity<CategoryResponse> createCategory(@RequestBody CategoryRequest categoryRequest) {
        return ResponseEntity.ok(service.createCategory(categoryRequest));
    }
    @PutMapping("/categoryUpdate/{categoryId}")
    public ResponseEntity<CategoryResponse> updateCategory(@PathVariable int categoryId, @RequestBody CategoryRequest categoryRequest) {
        return  ResponseEntity.ok(service.updateCategory(categoryId, categoryRequest));
    }

    @DeleteMapping("/categoryDelete/{categoryId}")
    public void deleteCategory(@PathVariable int categoryId) {
        service.deleteCategory(categoryId);
    }
}
