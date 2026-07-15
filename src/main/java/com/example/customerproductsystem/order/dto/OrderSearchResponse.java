package com.example.customerproductsystem.order.dto;

import com.example.customerproductsystem.order.entity.Order;
import com.example.customerproductsystem.order.entity.OrderStatus;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class OrderSearchResponse {
    private Long id;
    private String orderNumber;
    private String customerName;
    private String productName;
    private Integer quantity;
    private Long totalPrice;
    private LocalDateTime createdAt;
    private OrderStatus status;
    private String adminName;

    public static OrderSearchResponse from(Order order) {
        return new OrderSearchResponse(
                order.getId(),
                order.getOrderNumber(),
                order.getCustomer().getName(),
                order.getProduct().getName(),
                order.getQuantity(),
                order.getTotalPrice(),
                order.getCreatedAt(),
                order.getStatus(),
                order.getAdmin() != null ? order.getAdmin().getName() : null
        );
    }
}
