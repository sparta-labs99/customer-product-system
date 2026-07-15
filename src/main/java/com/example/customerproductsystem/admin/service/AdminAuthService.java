package com.example.customerproductsystem.admin.service;

import com.example.customerproductsystem.admin.dto.AdminLoginRequest;
import com.example.customerproductsystem.admin.dto.AdminSignupRequest;
import com.example.customerproductsystem.admin.dto.AdminSignupResponse;
import com.example.customerproductsystem.admin.entity.Admin;
import com.example.customerproductsystem.admin.entity.AdminStatus;
import com.example.customerproductsystem.admin.error.AdminException;
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
                .orElseThrow(AdminException.LoginFailed::new);

        validationPassword(request.password(), admin.getPassword());

        validateLoginStatus(admin.getStatus());

        return LoginAdmin.from(admin);
    }

    private void validateDuplicateEmail(String email) {
        if(adminRepository.existsByEmail(email)) {
            throw new AdminException.DuplicateEmail();
        }
    }

    private void validationPassword(String rawPassword, String encodedPassword) {
        boolean matches  = passwordEncoder.matches(rawPassword, encodedPassword);

        if(!matches) {
            throw new AdminException.LoginFailed();
        }
    }

    private void validateLoginStatus(AdminStatus status) {
        switch (status) {

            case ACTIVE -> {return;}

            case PENDING -> throw new AdminException.Pending();

            case REJECTED -> throw new AdminException.Rejected();

            case SUSPENDED -> throw new AdminException.Suspended();

            case INACTIVE -> throw new AdminException.Inactive();
        }
    }
}
