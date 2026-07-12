package com.example.customerproductsystem.product.entity;

import com.example.customerproductsystem.product.error.InvalidCategoryException;

import java.util.Arrays;

public enum Categories{
    ELECTRONICS,
    FASHION,
    FOOD,
    ELSE;

    public static Categories from(String value) {
        return Arrays.stream(values())
                .filter(x -> x.name().equalsIgnoreCase(value))
                .findFirst()
                .orElseThrow(InvalidCategoryException::new);
    }
}