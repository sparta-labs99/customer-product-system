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

    // 관리자 가입 신청 승인
    public void approve() {
        this.status = AdminStatus.ACTIVE;
        this.approvedAt = LocalDateTime.now();
        this.rejectedAt = null;
        this.rejectionReason = null;
    }

    //관리자 가입 신청 거부
    public void reject(String reason) {
        this.status = AdminStatus.REJECTED;
        this.rejectedAt = LocalDateTime.now();
        this.rejectionReason = reason;
        this.approvedAt = null;
    }

    // 최초 슈퍼 관리자 생성
    public static Admin createSuperAdmin(
            String name,
            String email,
            String encodePassword,
            String phoneNumber
    ) {
        Admin admin = new Admin();

        admin.name = name;
        admin.email = email;
        admin.password = encodePassword;
        admin.phoneNumber = phoneNumber;
        admin.role = AdminRole.SUPER_ADMIN;
        admin.status = AdminStatus.ACTIVE;
        admin.createdAt = LocalDateTime.now();
        admin.approvedAt = LocalDateTime.now();

        return admin;
    }

    // 관리자 정보 수정
    public void updateProfile(
            String name,
            String email,
            String phoneNumber
    ) {
        this.name = name;
        this.email = email;
        this.phoneNumber = phoneNumber;
    }

    // 역할 변경
    public void changeRole(AdminRole role) {
        this.role = role;
    }

    // 운영 상태 변경
    public void changeStatus(AdminStatus status) {
        this.status = status;
    }

    public void changePassword(String encodedPassword) {
        this.password = encodedPassword;
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
