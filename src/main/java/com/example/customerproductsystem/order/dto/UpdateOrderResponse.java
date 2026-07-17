package com.example.customerproductsystem.order.dto;

import com.example.customerproductsystem.order.entity.Order;
import com.example.customerproductsystem.order.entity.OrderStatus;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class UpdateOrderResponse {
    private Long id;
    private String orderNumber;
    private OrderStatus status;
    private LocalDateTime createdAt;
    private String adminName;

    public static UpdateOrderResponse from(Order order) {
        return new UpdateOrderResponse(
                order.getId(),
                order.getOrderNumber(),
                order.getStatus(),
                order.getCreatedAt(),
                order.getAdmin() != null ? order.getAdmin().getName() : null
        );
    }
}
