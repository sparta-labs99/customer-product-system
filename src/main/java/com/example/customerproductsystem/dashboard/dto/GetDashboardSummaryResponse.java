package com.example.customerproductsystem.dashboard.dto;

public record GetDashboardSummaryResponse(
        long totalAdmin, // 모든 관리자 수
        long activeAdmin, // 활성화된 관리자 수
        long totalCustomer, // 모든 고객 수
        long activeCustomer, // 활성화된 고객 수
        long totalProduct, // 모든 상품 수
        long limitStockProduct, // 재고 적음 상품 수
        long totalOrder, // 모든 주문
        long todayOrder, // 오늘 주문
        long totalReview, // 모든 리뷰
        Double averageRating // 평균 평점
) {
}
