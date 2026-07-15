package com.example.customerproductsystem.admin.service;

import com.example.customerproductsystem.admin.dto.MyProfileResponse;
import com.example.customerproductsystem.admin.dto.MyProfileUpdateRequest;
import com.example.customerproductsystem.admin.dto.PasswordChangeRequest;
import com.example.customerproductsystem.admin.entity.Admin;
import com.example.customerproductsystem.admin.error.AdminException;
import com.example.customerproductsystem.admin.repository.AdminRepository;
import com.example.customerproductsystem.common.config.PasswordEncoder;
import com.example.customerproductsystem.common.error.CustomException;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class AdminProfileService {

    private final AdminRepository adminRepository;
    private final PasswordEncoder passwordEncoder;

    @Transactional(readOnly = true)
    public MyProfileResponse getMyProfile(Long adminId) {
        Admin admin = findAdmin(adminId);
        return MyProfileResponse.from(admin);
    }

    @Transactional
    public MyProfileResponse updateMyProfile(Long adminId, MyProfileUpdateRequest request) {
        Admin admin = findAdmin(adminId);

        validateDuplicateEmailForUpdate(adminId, request.email());

        admin.updateProfile(request.name(), request.email(), request.phoneNumber());

        return MyProfileResponse.from(admin);
    }

    @Transactional
    public void changedPassword(Long adminId, PasswordChangeRequest request) {
        Admin admin = findAdmin(adminId);

        validateCurrentPassword(request.currentPassword(), admin.getPassword());

        String encodedNewPassword = passwordEncoder.encode(request.newPassword());

        admin.changePassword(encodedNewPassword);
    }

    // ID로 현재 관리자 계정 조회
    private Admin findAdmin(Long adminId) {
        return adminRepository.findById(adminId)
                .orElseThrow(() -> new AdminException.NotFound(adminId));
    }

    // 이메일 수정 시 이메일 중복검사
    private void validateDuplicateEmailForUpdate(Long adminId, String email) {

        boolean duplicate = adminRepository.existsByEmailAndIdNot(email, adminId);

        if(duplicate) {
            throw new AdminException.DuplicateEmail();
        }
    }

    // 비밀번호 비교
    private void validateCurrentPassword(String currentPassword, String encodePassword) {
        boolean matches = passwordEncoder.matches(currentPassword, encodePassword);

        if(!matches) {
            throw new AdminException.CurrentPasswordMismatch();
        }
    }
}
