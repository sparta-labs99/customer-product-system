package com.example.customerproductsystem.review.entity;

import com.example.customerproductsystem.review.error.InvalidReviewStatusException;

import java.util.Arrays;

public enum ReviewStatus {
    NORMAL, // 일반
    DELETED; // 삭제됨

    public static ReviewStatus from(String value) {
        return Arrays.stream(values())
                .filter(x -> x.name().equalsIgnoreCase(value))
                .findFirst()
                .orElseThrow(InvalidReviewStatusException::new);
    }
}
