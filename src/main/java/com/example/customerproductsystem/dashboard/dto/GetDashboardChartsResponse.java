package com.example.customerproductsystem.dashboard.dto;

import com.example.customerproductsystem.customer.dto.CustomerStatusCount;
import com.example.customerproductsystem.product.dto.ProductCategoryCount;
import com.example.customerproductsystem.review.dto.ReviewRatingCount;

import java.util.List;

public record GetDashboardChartsResponse(
        List<ReviewRatingCount> ratingDistribution,
        List<CustomerStatusCount> customerStatusDistribution,
        List<ProductCategoryCount> categoryDistribution
) {
}
