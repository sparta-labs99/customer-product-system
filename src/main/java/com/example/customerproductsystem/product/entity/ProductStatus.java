package com.example.customerproductsystem.product.entity;

import com.example.customerproductsystem.common.error.CustomException;
import com.example.customerproductsystem.common.error.ErrorCode;
import lombok.Getter;

import java.util.Arrays;

@Getter
public enum ProductStatus{
    FOR_SALE("판매중"),
    OUT_OF_STOCK("품절"),
    DISCONTINUED("단종됨"),
    DELETED("삭제됨");

    // 에러코드를 위한 상태 String 값 추가
    private final String description;

    ProductStatus(String description) {
        this.description = description;
    }

    // 주문 가능 상태 여부 판단
    public boolean isOrderable(int stock) {

        return this != DISCONTINUED && (this != OUT_OF_STOCK || stock != 0);
    }

    public static ProductStatus from(String value) {

        return Arrays.stream(values())
                .filter(x -> x.name().equalsIgnoreCase(value))
                .findFirst()
                .orElseThrow(()->new CustomException(ErrorCode.INVALID_PRODUCT_STATUS));
    }
}