package com.example.customerproductsystem.auth;

import com.example.customerproductsystem.admin.entity.Admin;
import com.example.customerproductsystem.admin.entity.AdminRole;
import com.example.customerproductsystem.admin.repository.AdminRepository;
import com.example.customerproductsystem.common.config.PasswordEncoder;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Component
@RequiredArgsConstructor
public class AdminInitializer implements CommandLineRunner {
    // 프로그램 실행시 슈퍼 관리자 생성을 위한 클래스입니다

    private final AdminRepository adminRepository;
    private final PasswordEncoder passwordEncoder;

    @Override
    @Transactional
    public void run(String... args) {
        if (adminRepository.existsByRole(
                AdminRole.SUPER_ADMIN
        )) {
            return;
        }

        Admin superAdmin =
                Admin.createSuperAdmin(
                        "최고 관리자",
                        "super@example.com",
                        passwordEncoder.encode(
                                "superadmin123"
                        ),
                        "010-0000-0000"
                );

        adminRepository.save(superAdmin);

        if (!adminRepository.existsByRole(AdminRole.OPERATION_ADMIN)) {
            Admin opAdmin = Admin.create(
                    "운영 관리자",
                    "opadmin@example.com",
                    passwordEncoder.encode("superadmin123"),
                    "010-1111-1111",
                    AdminRole.OPERATION_ADMIN
            );
            opAdmin.approve();
            adminRepository.save(opAdmin);
        }

        if (!adminRepository.existsByRole(AdminRole.CS_ADMIN)) {
            Admin csAdmin = Admin.create(
                    "CS 관리자",
                    "csadmin@example.com",
                    passwordEncoder.encode("superadmin123"),
                    "010-2222-2222",
                    AdminRole.CS_ADMIN
            );
            csAdmin.approve();
            adminRepository.save(csAdmin);
        }
    }
}