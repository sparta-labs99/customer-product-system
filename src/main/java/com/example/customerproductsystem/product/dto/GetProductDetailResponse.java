package com.example.customerproductsystem.product.dto;

import com.example.customerproductsystem.product.entity.Categories;
import com.example.customerproductsystem.product.entity.Product;
import com.example.customerproductsystem.product.entity.ProductStatus;
import com.example.customerproductsystem.review.dto.GetReviewResponse;
import com.example.customerproductsystem.review.dto.ReviewRatingCount;

import java.time.LocalDateTime;
import java.util.List;

public record GetProductDetailResponse(
        Long id,
        String name,
        Categories category,
        int price, int stock,
        ProductStatus status,
        String adminName,
        String adminEmail,
        List<GetReviewResponse> reviewList,
        List<ReviewRatingCount> reviewRatingCount,
        long totalReviewCount,
        double averageReviewRating,
        LocalDateTime createdAt,
        LocalDateTime updatedAt)
{

    public static GetProductDetailResponse from(
            Product product,
            List<GetReviewResponse> reviewList,
            List<ReviewRatingCount> reviewRatingCount,
            long totalReviewCount,
            double averageReviewRating
    ) {

        return new GetProductDetailResponse(
                product.getId(),
                product.getName(),
                product.getCategory(),
                product.getPrice(),
                product.getStock(),
                product.getStatus(),
                product.getAdmin().getName(),
                product.getAdmin().getEmail(),
                reviewList,
                reviewRatingCount,
                totalReviewCount,
                averageReviewRating,
                product.getCreatedAt(),
                product.getUpdatedAt()
        );
    }
}
