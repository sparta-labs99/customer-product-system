package com.example.customerproductsystem.customer.dto;

import com.example.customerproductsystem.customer.entity.Customer;
import com.example.customerproductsystem.customer.entity.CustomerStatus;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
public class UpdateCustomerResponse {

    private final Long id;
    private final String name;
    private final String email;
    private final String phoneNumber;
    private final CustomerStatus status;
    private final LocalDateTime updatedAt;

    public UpdateCustomerResponse(Customer customer) {
        this.id = customer.getId();
        this.name = customer.getName();
        this.email = customer.getEmail();
        this.phoneNumber = customer.getPhoneNumber();
        this.status = customer.getStatus();
        this.updatedAt = customer.getUpdatedAt();
    }
}