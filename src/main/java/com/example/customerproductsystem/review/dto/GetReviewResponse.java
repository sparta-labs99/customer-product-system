package com.example.customerproductsystem.review.dto;

import com.example.customerproductsystem.product.entity.Categories;
import com.example.customerproductsystem.review.entity.Review;
import com.example.customerproductsystem.review.entity.ReviewStatus;

import java.time.LocalDateTime;

public record GetReviewResponse(
        Long id,
        String contents,
        int rating,
        ReviewStatus status,
        String orderNumber,
        Long productId,
        String productName,
        Categories productCategory,
        String customerName,
        LocalDateTime createdAt,
        LocalDateTime modifiedAt
) {
    public static GetReviewResponse from(Review review) {
        return new GetReviewResponse(
                review.getId(),
                review.getContents(),
                review.getRating(),
                review.getStatus(),
                review.getOrder().getOrderNumber(),
                review.getProduct().getId(),
                review.getProduct().getName(),
                review.getProduct().getCategory(),
                review.getCustomer().getName(),
                review.getCreatedAt(),
                review.getUpdatedAt()
        );
    }
}
