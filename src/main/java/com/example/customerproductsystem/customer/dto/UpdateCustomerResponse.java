package com.example.customerproductsystem.customer.dto;

import com.example.customerproductsystem.customer.entity.Customer;
import com.example.customerproductsystem.customer.entity.CustomerStatus;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class UpdateCustomerResponse {

    private final Long id;
    private final String name;
    private final String email;
    private final String phoneNumber;
    private final CustomerStatus status;
    private final LocalDateTime updatedAt;

    public static UpdateCustomerResponse from(Customer customer) {
        return new UpdateCustomerResponse(
                customer.getId(),
                customer.getName(),
                customer.getEmail(),
                customer.getPhoneNumber(),
                customer.getStatus(),
                customer.getUpdatedAt()
        );
    }
}