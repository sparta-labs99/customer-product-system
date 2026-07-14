package com.example.customerproductsystem.product.controller;

import com.example.customerproductsystem.product.dto.*;
import com.example.customerproductsystem.product.service.ProductService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class ProductController {

    private final ProductService productService;

    @PostMapping("/products")
    public ResponseEntity<CreateProductResponse> createProduct (
            @Valid @RequestBody CreateProductRequest request) {

        // 상품 등록
        CreateProductResponse result = productService.create(request);

        return ResponseEntity.status(HttpStatus.CREATED).body(result);
    }

    @GetMapping("/products/{id}")
    public ResponseEntity<GetProductResponse> getProduct(
            @PathVariable Long id) {

        GetProductResponse result = productService.getOne(id);

        return ResponseEntity.status(HttpStatus.OK).body(result);
    }

    @GetMapping("/products")
    public ResponseEntity<List<GetProductResponse>> getAllProducts(
            @RequestParam(required = false) String keyword,
            @RequestParam(required = false) String status,
            Pageable pageable
            ) {

        List<GetProductResponse> result = productService.getAll(keyword, status, pageable);

        return ResponseEntity.status(HttpStatus.OK).body(result);
    }

    @PutMapping("/products/{id}")
    public ResponseEntity<UpdateProductResponse> updateProduct(
            @PathVariable Long id,
            @Valid @RequestBody UpdateProductRequest request) {

        UpdateProductResponse result = productService.update(id, request);

        return ResponseEntity.status(HttpStatus.OK).body(result);
    }

    @PatchMapping("/products/{id}/stock")
    public ResponseEntity<UpdateProductResponse> updateProductStock(
            @PathVariable Long id,
            @RequestParam int count) {

        UpdateProductResponse result = productService.updateStock(id, count);

        return ResponseEntity.status(HttpStatus.OK).body(result);
    }

    @PatchMapping("/products/{id}/status")
    public ResponseEntity<UpdateProductResponse> updateProductStatus(
            @PathVariable Long id,
            @RequestParam String status) {

        UpdateProductResponse result = productService.updateStatus(id, status);

        return ResponseEntity.status(HttpStatus.OK).body(result);
    }

    @DeleteMapping("/products/{id}")
    public ResponseEntity<Void> deleteProduct(
            @PathVariable Long id) {

        productService.delete(id);

        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }
}
