package com.example.customerproductsystem.product.entity;

import com.example.customerproductsystem.common.error.CustomException;
import com.example.customerproductsystem.common.error.ErrorCode;

import java.util.Arrays;

public enum ProductStatus{
    FOR_SALE, // 판매 중
    OUT_OF_STOCK, // 품절
    DISCONTINUED, // 단종
    DELETED; // 관리자에 의해 삭제됨

    public static ProductStatus from(String value) {
        return Arrays.stream(values())
                .filter(x -> x.name().equalsIgnoreCase(value))
                .findFirst()
                .orElseThrow(()->new CustomException(ErrorCode.INVALID_PRODUCT_STATUS));
    }
}