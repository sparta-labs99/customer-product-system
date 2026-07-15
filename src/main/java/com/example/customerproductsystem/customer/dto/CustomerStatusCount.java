package com.example.customerproductsystem.customer.dto;

import com.example.customerproductsystem.customer.entity.CustomerStatus;

public record CustomerStatusCount(
        CustomerStatus status,
        Long count
) {}

