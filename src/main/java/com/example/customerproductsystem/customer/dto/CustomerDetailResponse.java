package com.example.customerproductsystem.customer.dto;

import com.example.customerproductsystem.customer.entity.Customer;
import com.example.customerproductsystem.customer.entity.CustomerStatus;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
public class CustomerDetailResponse {

    private final Long id;
    private final String name;
    private final String email;
    private final String phoneNumber;
    private final CustomerStatus status;
    private final LocalDateTime createdAt;
    private final LocalDateTime updatedAt;
    private final long totalOrderCount;
    private final long totalPurchaseAmount;

    // 엔티티와 주문 통계 데이터를 함께 받는 생성자
    public CustomerDetailResponse(Customer customer, long totalOrderCount, long totalPurchaseAmount) {
        this.id = customer.getId();
        this.name = customer.getName();
        this.email = customer.getEmail();
        this.phoneNumber = customer.getPhoneNumber();
        this.status = customer.getStatus();
        this.createdAt = customer.getCreatedAt();
        this.updatedAt = customer.getUpdatedAt();
        this.totalOrderCount = totalOrderCount;
        this.totalPurchaseAmount = totalPurchaseAmount;
    }
}