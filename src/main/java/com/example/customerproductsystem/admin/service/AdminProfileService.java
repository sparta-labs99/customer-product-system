package com.example.customerproductsystem.admin.service;

import com.example.customerproductsystem.admin.dto.MyProfileResponse;
import com.example.customerproductsystem.admin.dto.MyProfileUpdateRequest;
import com.example.customerproductsystem.admin.dto.PasswordChangeRequest;
import com.example.customerproductsystem.admin.entity.Admin;
import com.example.customerproductsystem.admin.repository.AdminRepository;
import com.example.customerproductsystem.common.Error.CustomException;
import com.example.customerproductsystem.common.config.PasswordEncoder;
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
                .orElseThrow(() -> new CustomException(HttpStatus.NOT_FOUND, "관리자를 찾을 수 없습니다."));
    }

    // 이메일 수정 시 이메일 중복검사
    private void validateDuplicateEmailForUpdate(Long adminId, String email) {

        boolean duplicate = adminRepository.existsByEmailAndIdNot(email, adminId);

        if(duplicate) {
            throw new CustomException(HttpStatus.CONFLICT,"이미 사용 중인 이메일입니다. ");
        }
    }

    // 비밀번호 비교
    private void validateCurrentPassword(String currentPassword, String encodePassword) {
        boolean matches = passwordEncoder.matches(currentPassword, encodePassword);

        if(!matches) {
            throw new CustomException(HttpStatus.BAD_REQUEST, "현재 비밀번호가 일치하지 않습니다. ");
        }
    }
}
