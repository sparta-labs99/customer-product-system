package com.example.customerproductsystem.customer.entity;

import com.example.customerproductsystem.common.entity.BaseEntity;
import com.example.customerproductsystem.customer.dto.UpdateCustomerRequest;
import com.example.customerproductsystem.common.error.CustomException;
import com.example.customerproductsystem.customer.error.CustomerException;
import org.springframework.http.HttpStatus;
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

    public void updateStatus(CustomerStatus newStatus) {
        if (this.status == CustomerStatus.INACTIVE) { // 현재 상태가 탈퇴
            throw new CustomerException.AlreadyDeleted(this.id);
        }
        if (newStatus == CustomerStatus.INACTIVE) { // 변경될 상태가 탈퇴 (withdrawCustomer api 통해서만 탈퇴 가능)
            throw new CustomException(HttpStatus.BAD_REQUEST, "상태 변경 API를 통해서는 탈퇴 처리를 할 수 없습니다.");
        }
        if (!this.status.canChangeTo(newStatus)) {
            throw new CustomException(HttpStatus.BAD_REQUEST, 
                "상태 변경이 불가능합니다. (" + this.status.name() + " -> " + newStatus.name() + ")");
        }
        this.status = newStatus;
    }

    public void withdrawCustomer() {
        if (this.status == CustomerStatus.INACTIVE) {
            throw new CustomerException.AlreadyDeleted(this.id);
        }
        this.status = CustomerStatus.INACTIVE;
    }
}