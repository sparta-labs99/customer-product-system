package com.example.customerproductsystem.product.dto;

import com.example.customerproductsystem.product.entity.ProductEnum;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
public class CreateProductResponse {

    private final Long id;
    private final String name;
    private final ProductEnum.Category category;
    private final int price;
    private final int stock;
    private final ProductEnum.ProductStatus status;
    private final LocalDateTime createdAt;
    private final LocalDateTime modifiedAt;

    public CreateProductResponse(
            Long id, String name, ProductEnum.Category category,
            int price, int stock, ProductEnum.ProductStatus status,
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
