package com.example.customerproductsystem.dashboard.dto;

public record GetDashboardWidgetsResponse(
        long totalSales, // 총 매출
        long todaySales, // 오늘 매출
        long pendingOrder, // 준비 중 주문
        long shippingOrder, // 배송 중 주문
        long completeOrder, // 배송 완료 주문
        long limitStockProduct, // 재고 부족 상품
        long outStockProduct // 재고 없음 상품
) {
}
