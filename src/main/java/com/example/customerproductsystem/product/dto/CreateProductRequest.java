package com.example.customerproductsystem.product.dto;

import com.example.customerproductsystem.admin.entity.Admin;
import com.example.customerproductsystem.product.entity.Categories;
import com.example.customerproductsystem.product.entity.Product;
import com.example.customerproductsystem.product.entity.ProductStatus;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Getter;

@Getter
public class CreateProductRequest {

    @NotBlank
    @Size(max = 100)
    private String name;

    @NotBlank
    @Size(max = 50)
    private String category;

    private int price;

    private int stock;

    @NotBlank
    @Size(max = 20)
    private String status;

    // DTO를 엔티티로 변환
    public Product toEntity(Categories category, ProductStatus status, Admin admin) {
        return Product.builder()
                .name(this.name)
                .category(category)
                .price(this.price)
                .stock(this.stock)
                .status(status)
                .admin(admin)
                .build();
    }
}
