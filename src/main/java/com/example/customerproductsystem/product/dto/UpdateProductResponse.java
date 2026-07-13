package com.example.customerproductsystem.product.dto;

import com.example.customerproductsystem.product.entity.Categories;
import com.example.customerproductsystem.product.entity.ProductStatus;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
public class UpdateProductResponse {

    private final Long id;
    private final String name;
    private final Categories category;
    private final int price;
    private final int stock;
    private final ProductStatus status;
    private final LocalDateTime createdAt;
    private final LocalDateTime modifiedAt;

    public UpdateProductResponse(
            Long id, String name, Categories category,
            int price, int stock, ProductStatus status,
            LocalDateTime createdAt, LocalDateTime modifiedAt) {

        this.id = id;
        this.name = name;
        this.category = category;
        this.price = price;
        this.stock = stock;
        this.status = status;
        this.createdAt = createdAt;
        this.modifiedAt = modifiedAt;
    }
}
