package com.example.customerproductsystem.product.dto;

import com.example.customerproductsystem.product.entity.Categories;
import com.example.customerproductsystem.product.entity.Product;
import com.example.customerproductsystem.product.entity.ProductStatus;

import java.time.LocalDateTime;

public record UpdateProductResponse(
        Long id,
        String name,
        Categories category,
        int price,
        int stock,
        ProductStatus status,
        String adminName,
        String adminEmail,
        LocalDateTime createdAt,
        LocalDateTime modifiedAt
) {
    public static UpdateProductResponse from(Product product) {
        return new UpdateProductResponse(
                product.getId(),
                product.getName(),
                product.getCategory(),
                product.getPrice(),
                product.getStock(),
                product.getStatus(),
                product.getAdmin().getName(),
                product.getAdmin().getEmail(),
                product.getCreatedAt(),
                product.getUpdatedAt()
        );
    }
}
