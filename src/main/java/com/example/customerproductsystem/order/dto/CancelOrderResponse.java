package com.example.customerproductsystem.order.dto;

import com.example.customerproductsystem.order.entity.Order;
import com.example.customerproductsystem.order.entity.OrderStatus;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class CancelOrderResponse {
    private Long id;
    private String orderNumber;
    private OrderStatus status;
    private String cancelReason;
    private LocalDateTime createdAt;

    public static CancelOrderResponse from(Order order) {
        return new CancelOrderResponse(
                order.getId(),
                order.getOrderNumber(),
                order.getStatus(),
                order.getCancelReason(),
                order.getCreatedAt()
        );
    }
}
