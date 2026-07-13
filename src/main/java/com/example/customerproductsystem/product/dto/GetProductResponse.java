package com.example.customerproductsystem.product.dto;

import com.example.customerproductsystem.product.entity.Categories;
import com.example.customerproductsystem.product.entity.ProductStatus;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
public class GetProductResponse {

    private final Long id;
    private final String name;
    private final Categories category;
    private final int price;
    private final int stock;
    private final ProductStatus status;

    private final String adminName;
    private final String adminEmail;

    private final LocalDateTime createdAt;
    private final LocalDateTime updatedAt;

    public GetProductResponse(
            Long id, String name, Categories category,
            int price, int stock, ProductStatus status,
            String adminName, String adminEmail,
            LocalDateTime createdAt, LocalDateTime updatedAt) {
        this.id = id;
        this.name = name;
        this.category = category;
        this.price = price;
        this.stock = stock;
        this.status = status;
        this.adminName = adminName;
        this.adminEmail = adminEmail;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
    }
}
