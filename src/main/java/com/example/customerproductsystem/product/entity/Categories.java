package com.example.customerproductsystem.product.entity;

import com.example.customerproductsystem.product.error.InvalidCategoryException;

import java.util.Arrays;

public enum Categories{
    ELECTRONICS, // 전자 제품
    FASHION, // 패션, 의류
    FOOD, // 식품
    ELSE; // 기타

    public static Categories from(String value) {
        return Arrays.stream(values())
                .filter(x -> x.name().equalsIgnoreCase(value))
                .findFirst()
                .orElseThrow(InvalidCategoryException::new);
    }
}