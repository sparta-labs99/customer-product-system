package com.example.customerproductsystem.order.dto;

import com.example.customerproductsystem.admin.entity.AdminRole;
import com.example.customerproductsystem.order.entity.Order;
import com.example.customerproductsystem.order.entity.OrderStatus;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class OrderDetailResponse {
    // 기본 정보
    private String orderNumber;
    private String customerName;
    private String customerEmail;
    private String productName;
    private Integer quantity;
    private Long totalPrice;
    private LocalDateTime createdAt;
    private OrderStatus status;
    private String cancelReason;

    // 관리자 정보
    private String adminName;
    private String adminEmail;
    private AdminRole adminRole;

    public static OrderDetailResponse from(Order order) {
        boolean hasAdmin = order.getAdmin() != null;
        
        return new OrderDetailResponse(
                order.getOrderNumber(),
                order.getCustomer().getName(),
                order.getCustomer().getEmail(),
                order.getProduct().getName(),
                order.getQuantity(),
                order.getTotalPrice(),
                order.getCreatedAt(),
                order.getStatus(),
                order.getCancelReason(),
                hasAdmin ? order.getAdmin().getName() : null,
                hasAdmin ? order.getAdmin().getEmail() : null,
                hasAdmin ? order.getAdmin().getRole() : null
        );
    }
}
