package com.example.customerproductsystem.review.dto;

// 평점 별 리뷰 갯수를 저장하기 위한 DTO
public record ReviewRatingCount(
        Integer rating,
        Long count
) {

}
