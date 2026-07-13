package com.example.customerproductsystem.admin.service;

import com.example.customerproductsystem.admin.dto.AdminLoginRequest;
import com.example.customerproductsystem.admin.dto.AdminSignupRequest;
import com.example.customerproductsystem.admin.dto.AdminSignupResponse;
import com.example.customerproductsystem.admin.entity.Admin;
import com.example.customerproductsystem.admin.entity.AdminStatus;
import com.example.customerproductsystem.admin.repository.AdminRepository;
import com.example.customerproductsystem.auth.LoginAdmin;
import com.example.customerproductsystem.common.error.CustomException;
import com.example.customerproductsystem.common.config.PasswordEncoder;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


@Service
@RequiredArgsConstructor
public class AdminAuthService {

    private final AdminRepository adminRepository;
    private final PasswordEncoder passwordEncoder;

    @Transactional
    public AdminSignupResponse signup(AdminSignupRequest request) {

        validateDuplicateEmail(request.email());

        String encodePassword = passwordEncoder.encode(request.password());

        Admin admin = Admin.create(
                request.name(),
                request.email(),
                encodePassword,
                request.phoneNumber(),
                request.role()

        );

        Admin savedAdmin = adminRepository.save(admin);

        return AdminSignupResponse.from(savedAdmin);
    }

    @Transactional(readOnly = true)
    public LoginAdmin login(AdminLoginRequest request) {

        Admin admin = adminRepository
                .findByEmail(request.email())
                .orElseThrow(() -> new CustomException(HttpStatus.UNAUTHORIZED, "이메일 또는 비밀번호가 일치하지 않습니다."));

        validationPassword(request.password(), admin.getPassword());

        validateLoginStatus(admin.getStatus());

        return LoginAdmin.from(admin);
    }

    private void validateDuplicateEmail(String email) {
        if(adminRepository.existsByEmail(email)) {
            throw new CustomException(HttpStatus.CONFLICT, "이미 사용 중인 이메일입니다.");
        }
    }

    private void validationPassword(String rawPassword, String encodedPassword) {
        boolean matches  = passwordEncoder.matches(rawPassword, encodedPassword);

        if(!matches) {
            throw new CustomException(HttpStatus.UNAUTHORIZED, "이메일 또는 비밀번호가 일치하지 않습니다");
        }
    }

    private void validateLoginStatus(AdminStatus status) {
        switch (status) {
            case ACTIVE -> {
                return;
            }

            case PENDING -> throw new CustomException(
                    HttpStatus.FORBIDDEN,
                    "승인 대기 중인 관리자입니다."
            );

            case REJECTED -> throw new CustomException(
                    HttpStatus.FORBIDDEN,
                    "가입 신청이 거부된 관리자입니다."
            );

            case SUSPENDED -> throw new CustomException(
                    HttpStatus.FORBIDDEN,
                    "정지된 관리자입니다."
            );

            case INACTIVE -> throw new CustomException(
                    HttpStatus.FORBIDDEN,
                    "비활성된 관리자입니다."
            );
        }
    }
}
