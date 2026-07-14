package com.example.customerproductsystem.product.controller;

import com.example.customerproductsystem.auth.LoginAdmin;
import com.example.customerproductsystem.product.dto.*;
import com.example.customerproductsystem.product.service.ProductService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class ProductController {

    private final ProductService productService;

    // 상품 등록 (슈퍼 관리자, 운영 관리자)
    @PostMapping("/products")
    public ResponseEntity<CreateProductResponse> createProduct (
            @Valid @RequestBody CreateProductRequest request,
            @SessionAttribute(name="LOGIN_ADMIN",required = false) LoginAdmin sessionAdmin
    ) {

        CreateProductResponse result = productService.create(request, sessionAdmin);

        return ResponseEntity.status(HttpStatus.CREATED).body(result);
    }

    // 상품 단건 조회
    @GetMapping("/products/{id}")
    public ResponseEntity<GetProductDetailResponse> getProduct(
            @PathVariable Long id
    ) {

        GetProductDetailResponse result = productService.getOne(id);

        return ResponseEntity.status(HttpStatus.OK).body(result);
    }

    // 상품 전체 조회
    @GetMapping("/products")
    public ResponseEntity<List<GetProductResponse>> getAllProducts(
            @RequestParam(required = false) String keyword,
            @RequestParam(required = false) String status,
            Pageable pageable // / url에서 자동으로 page, size, sort 값을 저장
    ) {

        List<GetProductResponse> result = productService.getAll(keyword, status, pageable);

        return ResponseEntity.status(HttpStatus.OK).body(result);
    }

    // 상품 업데이트
    @PutMapping("/products/{id}")
    public ResponseEntity<UpdateProductResponse> updateProduct(
            @PathVariable Long id,
            @Valid @RequestBody UpdateProductRequest request
    ) {

        UpdateProductResponse result = productService.update(id, request);

        return ResponseEntity.status(HttpStatus.OK).body(result);
    }

    // 상품 재고 변경
    @PatchMapping("/products/{id}/stock")
    public ResponseEntity<UpdateProductResponse> updateProductStock(
            @PathVariable Long id,
            @RequestParam int count
    ) {

        UpdateProductResponse result = productService.updateStock(id, count);

        return ResponseEntity.status(HttpStatus.OK).body(result);
    }

    // 상품 상태 변경
    @PatchMapping("/products/{id}/status")
    public ResponseEntity<UpdateProductResponse> updateProductStatus(
            @PathVariable Long id,
            @RequestParam String status
    ) {

        UpdateProductResponse result = productService.updateStatus(id, status);

        return ResponseEntity.status(HttpStatus.OK).body(result);
    }

    // 상품 삭제
    @DeleteMapping("/products/{id}")
    public ResponseEntity<Void> deleteProduct(
            @PathVariable Long id
    ) {

        productService.delete(id);

        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }
}
