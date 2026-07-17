package com.example.customerproductsystem.dashboard.dto;

import com.example.customerproductsystem.order.dto.OrderDetailResponse;

import java.util.List;

public record GetDashboardOrdersResponse(
       List<OrderDetailResponse> orders
) {
}
