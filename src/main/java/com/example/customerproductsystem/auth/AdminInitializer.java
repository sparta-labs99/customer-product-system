package com.example.customerproductsystem.auth;

import com.example.customerproductsystem.admin.entity.Admin;
import com.example.customerproductsystem.admin.entity.AdminRole;
import com.example.customerproductsystem.admin.repository.AdminRepository;
import com.example.customerproductsystem.common.config.PasswordEncoder;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

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
    }
}
