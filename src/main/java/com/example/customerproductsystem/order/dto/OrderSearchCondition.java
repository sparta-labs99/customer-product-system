package com.example.customerproductsystem.order.dto;

import com.example.customerproductsystem.order.entity.OrderStatus;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class OrderSearchCondition {
    private String keyword; // 주문번호 또는 고객명
    private OrderStatus status; // 상태 필터
    private String sortBy = "createdAt"; // 정렬 기준 (quantity, totalPrice, createdAt)
    private String direction = "desc"; // 정렬 순서 (asc, desc)
}
