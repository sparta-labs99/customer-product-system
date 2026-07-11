package com.example.customerproductsystem.admin.entity;

import jakarta.persistence.*;

import java.time.LocalDateTime;

@Entity
@Table(
        name = "admins",
        uniqueConstraints = {
                @UniqueConstraint(
                        name = "uk_admin_email",
                        columnNames = "email"
                )
        }
)
public class Admin {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private String email;

    @Column(nullable = false)
    protected String password;

    @Column(nullable = false, length = 13)
    private String phoneNumber;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private AdminRole role;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private AdminStatus status;

    @Column(nullable = false)
    private LocalDateTime createdAt;

    private LocalDateTime approvedAt;

    private LocalDateTime rejectedAt;

    @Column(length = 500)
    private String rejectionReason;

    protected Admin() {
    }

    private Admin(
            String name,
            String email,
            String encodedPassword,
            String phoneNumber,
            AdminRole role
    ) {
        this.name = name;
        this.email = email;
        this.password = encodedPassword;
        this.phoneNumber = phoneNumber;
        this.role = role;
        this.status = AdminStatus.PENDING; // 승인대기 상태로 시작
        this.createdAt = LocalDateTime.now();
    }

    public static Admin create(
            String name,
            String email,
            String encodedPassword,
            String phoneNumber,
            AdminRole role
    ) {
        return new Admin(
                name,
                email,
                encodedPassword,
                phoneNumber,
                role
        );
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public AdminRole getRole() {
        return role;
    }

    public AdminStatus getStatus() {
        return status;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public LocalDateTime getApprovedAt() {
        return approvedAt;
    }

    public LocalDateTime getRejectedAt() {
        return rejectedAt;
    }

    public String getRejectionReason() {
        return rejectionReason;
    }
}
