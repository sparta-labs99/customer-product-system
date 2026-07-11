package com.example.customerproductsystem.product.controller;

import com.example.customerproductsystem.product.dto.*;
import com.example.customerproductsystem.product.service.ProductService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
public class ProductController {

    private final ProductService productService;

    @PostMapping("/products")
    public ResponseEntity<CreateProductResponse> create (
            @Valid @RequestBody CreateProductRequest request) {

        CreateProductResponse result = productService.create(request);

        return ResponseEntity.status(HttpStatus.CREATED).body(result);
    }

    @GetMapping("/products/{id}")
    public ResponseEntity<GetProductResponse> getOne(
            @PathVariable Long id) {

        GetProductResponse result = productService.getOne(id);

        return ResponseEntity.status(HttpStatus.OK).body(result);
    }

    @PutMapping("/products/{id}")
    public ResponseEntity<UpdateProductResponse> update(
            @PathVariable Long id,
            @Valid @RequestBody UpdateProductRequest request) {

        UpdateProductResponse result = productService.update(id, request);

        return ResponseEntity.status(HttpStatus.OK).body(result);
    }

    @PatchMapping("/products/{id}/stock")
    public ResponseEntity<UpdateProductResponse> updateStock(
            @PathVariable Long id,
            @RequestParam int count) {

        UpdateProductResponse result = productService.updateStock(id, count);

        return ResponseEntity.status(HttpStatus.OK).body(result);
    }

    @PatchMapping("/products/{id}/status")
    public ResponseEntity<UpdateProductResponse> updateStatus(
            @PathVariable Long id,
            @RequestParam String status) {

        UpdateProductResponse result = productService.updateStatus(id, status);

        return ResponseEntity.status(HttpStatus.OK).body(result);
    }

    @DeleteMapping("/products/{id}")
    public ResponseEntity<Void> delete(
            @PathVariable Long id) {

        productService.delete(id);

        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }
}
