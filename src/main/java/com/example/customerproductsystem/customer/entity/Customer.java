package com.example.customerproductsystem.customer.entity;

import com.example.customerproductsystem.common.entity.BaseEntity;
import com.example.customerproductsystem.customer.dto.UpdateCustomerRequest;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "customers")
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Customer extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "name", nullable = false, length = 50)
    private String name;

    @Column(name = "email", nullable = false, unique = true, length = 100)
    private String email;

    @Column(name = "password", nullable = false, length = 255)
    private String password;

    @Column(name = "phone_number", nullable = false, length = 20)
    private String phoneNumber;

    @Enumerated(EnumType.STRING)
    @Column(name = "status", nullable = false, length = 20)
    private CustomerStatus status;

    @Builder
    public Customer(String name, String email, String password, String phoneNumber, CustomerStatus status) {
        this.name = name;
        this.email = email;
        this.password = password;
        this.phoneNumber = phoneNumber;
        this.status = status;
    }

    public void updateCustomer(UpdateCustomerRequest request) {
        if (request.getName() != null) {
            this.name = request.getName();
        }
        if (request.getEmail() != null) {
            this.email = request.getEmail();
        }
        if (request.getPhoneNumber() != null) {
            this.phoneNumber = request.getPhoneNumber();
        }
    }

    public void updateStatus(CustomerStatus status) {
        this.status = status;
    }

    public void withdrawCustomer() {
        this.status = CustomerStatus.INACTIVE;
    }
}