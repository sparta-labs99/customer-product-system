package com.example.customerproductsystem.admin.service;

import com.example.customerproductsystem.admin.dto.AdminRejectRequest;
import com.example.customerproductsystem.admin.entity.Admin;
import com.example.customerproductsystem.admin.entity.AdminStatus;
import com.example.customerproductsystem.admin.repository.AdminRepository;
import com.example.customerproductsystem.common.error.CustomException;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AdminManagementService {

    private final AdminRepository adminRepository;

    // PENDING → ACTIVE
    @Transactional
    public void approveAdmin(Long adminId) {

        Admin admin = getAdmin(adminId);

        validPendingStatus(admin);

        admin.approve();
    }

    // PENDING → REJECTED
    @Transactional
    public void rejectAdmin(Long adminId, AdminRejectRequest request){
        Admin admin = getAdmin(adminId);

        validPendingStatus(admin);

        admin.reject(request.reason());
    }

    private Admin getAdmin(Long adminId) {
        return adminRepository.findById(adminId)
                .orElseThrow(() -> new CustomException(HttpStatus.NOT_FOUND, "관리자를 찾을 수 없습니다. "));
    }

    private void validPendingStatus(Admin admin) {
        if(admin.getStatus() != AdminStatus.PENDING) {
            throw new CustomException(HttpStatus.BAD_REQUEST,"승인 대기 상태의 관리자만 승인 또는 거부할 수 있습니다. ");
        }
    }
}