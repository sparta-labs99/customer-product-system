package com.example.customerproductsystem.product.dto;

import com.example.customerproductsystem.product.entity.Categories;
import com.example.customerproductsystem.product.entity.Product;
import com.example.customerproductsystem.product.entity.ProductStatus;

import java.time.LocalDateTime;

public record GetProductResponse(
        Long id,
        String name,
        Categories category,
        int price, int stock,
        ProductStatus status,
        String adminName,
        String adminEmail,
        LocalDateTime createdAt,
        LocalDateTime updatedAt)
{

    public static GetProductResponse from(Product product) {
        return new GetProductResponse(
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
