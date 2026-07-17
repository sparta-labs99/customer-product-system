package com.example.customerproductsystem.dashboard.controller;

import com.example.customerproductsystem.common.response.ApiResponse;
import com.example.customerproductsystem.dashboard.dto.GetDashboardChartsResponse;
import com.example.customerproductsystem.dashboard.dto.GetDashboardOrdersResponse;
import com.example.customerproductsystem.dashboard.dto.GetDashboardSummaryResponse;
import com.example.customerproductsystem.dashboard.dto.GetDashboardWidgetsResponse;
import com.example.customerproductsystem.dashboard.service.DashboardService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class DashboardController {

    private final DashboardService dashboardService;

    // 대시보드 summary
    @GetMapping("/dashboard/summary")
    public ResponseEntity<ApiResponse<GetDashboardSummaryResponse>> getDashboardSummary(
    ) {
        GetDashboardSummaryResponse result = dashboardService.getSummary();
        return ResponseEntity.ok().body(ApiResponse.success(result));
    }

    // 대시보드 widgets
    @GetMapping("/dashboard/widgets")
    public ResponseEntity<ApiResponse<GetDashboardWidgetsResponse>> getDashboardWidgets(
    ) {
        GetDashboardWidgetsResponse result = dashboardService.getWidgets();
        return ResponseEntity.ok().body(ApiResponse.success(result));
    }

    // 대시보드 charts
    @GetMapping("/dashboard/charts")
    public ResponseEntity<ApiResponse<GetDashboardChartsResponse>> getDashboardCharts(
    ) {
        GetDashboardChartsResponse result = dashboardService.getCharts();
        return ResponseEntity.ok().body(ApiResponse.success(result));
    }

    // 대시보드 charts
    @GetMapping("/dashboard/orders")
    public ResponseEntity<ApiResponse<GetDashboardOrdersResponse>> getDashboardOrders(
    ) {
        GetDashboardOrdersResponse result = dashboardService.getOrders();
        return ResponseEntity.ok().body(ApiResponse.success(result));
    }
}
