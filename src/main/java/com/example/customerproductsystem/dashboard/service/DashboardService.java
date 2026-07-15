package com.example.customerproductsystem.dashboard.service;

import com.example.customerproductsystem.admin.entity.AdminStatus;
import com.example.customerproductsystem.admin.repository.AdminRepository;
import com.example.customerproductsystem.customer.dto.CustomerStatusCount;
import com.example.customerproductsystem.customer.entity.CustomerStatus;
import com.example.customerproductsystem.customer.repository.CustomerRepository;
import com.example.customerproductsystem.dashboard.dto.GetDashboardChartsResponse;
import com.example.customerproductsystem.dashboard.dto.GetDashboardOrdersResponse;
import com.example.customerproductsystem.dashboard.dto.GetDashboardSummaryResponse;
import com.example.customerproductsystem.dashboard.dto.GetDashboardWidgetsResponse;
import com.example.customerproductsystem.order.dto.OrderDetailResponse;
import com.example.customerproductsystem.order.entity.OrderStatus;
import com.example.customerproductsystem.order.repository.OrderRepository;
import com.example.customerproductsystem.product.dto.ProductCategoryCount;
import com.example.customerproductsystem.product.entity.ProductStatus;
import com.example.customerproductsystem.product.repository.ProductRepository;
import com.example.customerproductsystem.review.dto.ReviewRatingCount;
import com.example.customerproductsystem.review.entity.ReviewStatus;
import com.example.customerproductsystem.review.repository.ReviewRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;

@Service
@RequiredArgsConstructor
public class DashboardService {

    private final AdminRepository adminRepository;
    private final CustomerRepository customerRepository;
    private final OrderRepository orderRepository;
    private final ProductRepository productRepository;
    private final ReviewRepository reviewRepository;

    @Transactional(readOnly = true)
    public GetDashboardSummaryResponse getSummary(){

        // 전체 관리자 수
        long totalAdmin = adminRepository.count();

        // 활성화 관리자 수
        long activeAdmin = adminRepository.countByStatus(AdminStatus.ACTIVE);

        // 전체 고객 수
        long totalCustomer = customerRepository.count();

        // 활성화 고객 수
        long activeCustomer = customerRepository.countByStatus(CustomerStatus.ACTIVE);

        // 전체 상품 수
        long totalProduct = productRepository.countByStatusNot(ProductStatus.DELETED);

        // 재고 부족 상품 수
        long limitStockProduct = productRepository.countByStockLessThanEqualAndStatus(5, ProductStatus.FOR_SALE);

        // 전체 주문 수
        long totalOrder = orderRepository.count();

        // 오늘 주문 수
        LocalDate today = LocalDate.now();
        long todayOrder = orderRepository.countByCreatedAtBetween(
                today.atStartOfDay(),
                today.plusDays(1).atStartOfDay()
        );

        // 전체 리뷰 수
        long totalReview = reviewRepository.countByStatusNot(ReviewStatus.DELETED);

        // 평균 평점
        double totalRatingAverage = reviewRepository.getRatingAverage();

        return new GetDashboardSummaryResponse(
                totalAdmin,
                activeAdmin,
                totalCustomer,
                activeCustomer,
                totalProduct,
                limitStockProduct,
                totalOrder,
                todayOrder,
                totalReview,
                totalRatingAverage
        );
    }

    @Transactional(readOnly = true)
    public GetDashboardWidgetsResponse getWidgets(){

        // 총 매출
        long totalSales = orderRepository.getTotalSales(OrderStatus.CANCELED);

        // 오늘 매출
        LocalDate today = LocalDate.now();
        long todaySales = orderRepository.getTotalSalesBetween(
                OrderStatus.CANCELED,
                today.atStartOfDay(),
                today.plusDays(1).atStartOfDay()
        );

        // 준비중 주문 수
        long pendingOrder = orderRepository.countByStatus(OrderStatus.PENDING);

        // 배송중 주문 수
        long shippingOrder = orderRepository.countByStatus(OrderStatus.SHIPPING);

        // 배송완료 주문 수
        long completeOrder = orderRepository.countByStatus(OrderStatus.COMPLETED);

        // 재고 부족 상품 수
        long limitStockProduct = productRepository.countByStockLessThanEqualAndStatus(5, ProductStatus.FOR_SALE);

        // 재고 없음 상품 수
        long outStockProduct = productRepository.countByStatus(ProductStatus.OUT_OF_STOCK);

        return new GetDashboardWidgetsResponse(
                totalSales,
                todaySales,
                pendingOrder,
                shippingOrder,
                completeOrder,
                limitStockProduct,
                outStockProduct
        );
    }

    @Transactional(readOnly = true)
    public GetDashboardChartsResponse getCharts(){

        List<ReviewRatingCount> ratingDistribution =
                reviewRepository.getRatingDistribution();

        List<CustomerStatusCount> customerStatusDistribution =
                customerRepository.getStatusDistribution();

        List<ProductCategoryCount> categoryDistribution =
                productRepository.getCategoryDistribution();

        return new GetDashboardChartsResponse(
                ratingDistribution,
                customerStatusDistribution,
                categoryDistribution
        );
    }

    @Transactional(readOnly = true)
    public GetDashboardOrdersResponse getOrders(){

        List<OrderDetailResponse> dtos = orderRepository.findTop10ByOrderByCreatedAtDesc()
                .stream()
                .map(OrderDetailResponse::from)
                .toList();

        return new GetDashboardOrdersResponse(dtos);
    }
}
