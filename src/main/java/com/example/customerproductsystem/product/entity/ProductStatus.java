package com.example.customerproductsystem.product.entity;

import com.example.customerproductsystem.product.error.InvalidStatusException;

import java.util.Arrays;

public enum ProductStatus{
    FOR_SALE,
    OUT_OF_STOCK,
    DISCONTINUED,
    DELETED;

    public static ProductStatus from(String value) {
        return Arrays.stream(values())
                .filter(x -> x.name().equalsIgnoreCase(value))
                .findFirst()
                .orElseThrow(InvalidStatusException::new);
    }
}