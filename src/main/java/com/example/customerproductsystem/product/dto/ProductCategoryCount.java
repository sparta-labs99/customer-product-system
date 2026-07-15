package com.example.customerproductsystem.product.dto;

import com.example.customerproductsystem.product.entity.Categories;

public record ProductCategoryCount(
        Categories category,
        Long count
) {
}
