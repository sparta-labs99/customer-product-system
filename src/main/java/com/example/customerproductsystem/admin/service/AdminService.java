package com.example.customerproductsystem.admin.service;

import com.example.customerproductsystem.admin.dto.*;
import com.example.customerproductsystem.admin.entity.Admin;
import com.example.customerproductsystem.admin.entity.AdminRole;
import com.example.customerproductsystem.admin.entity.AdminStatus;
import com.example.customerproductsystem.admin.repository.AdminRepository;
import com.example.customerproductsystem.admin.repository.AdminSpecification;
import com.example.customerproductsystem.common.Error.CustomException;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class AdminService {

    private final AdminRepository adminRepository;

    @Transactional(readOnly = true)
    public AdminDetailResponse getAdmin(Long adminId) {

        Admin admin = findAdmin(adminId);

        return AdminDetailResponse.from(admin);
    }

    @Transactional
    public PageResponse<AdminSummaryResponse> getAdmins(AdminSearchCondition condition) {
        Sort sort = createSort(condition.getSortBy(), condition.getDirection());

        Pageable pageable = PageRequest.of(
                condition.getPage() - 1,
                condition.getSize(),
                sort
        );

        Specification<Admin> specification =
                Specification
                        .where(AdminSpecification.keywordContains(condition.getKeyword()))
                        .and(AdminSpecification.roleEquals(condition.getRole()))
                        .and(AdminSpecification.statusEquals(condition.getStatus()));

        Page<AdminSummaryResponse> responsePage = adminRepository
                .findAll(specification, pageable)
                .map(AdminSummaryResponse::from);

        return PageResponse.from((responsePage));
    }

    @Transactional
    public AdminUpdateResponse updateAdmin(Long adminId, AdminUpdateRequest request) {

        Admin admin = findAdmin(adminId);

        validateEmailForUpdate(adminId, request.email());

        admin.updateProfile(request.name(), request.email(), request.phoneNumber());

        return AdminUpdateResponse.from(admin);
    }

    // PENDING, REJECTED는 승인·거부 API에서만 다룬다.
    @Transactional
    public void changeAdminRole(Long adminId, AdminRoleChangeRequest request) {

        Admin admin = findAdmin(adminId);

        if (admin.getRole() == request.role()) {
            throw new CustomException(HttpStatus.BAD_REQUEST, "현재 역할과 동일한 역할로 변경할 수 없습니다.");
        }

        validateLastActiveSuperAdminRoleChange(admin, request.role());

        admin.changeRole(request.role());
    }

    @Transactional
    public void changeAdminStatus(Long adminId, AdminStatusChangeRequest request) {

        Admin admin = findAdmin(adminId);

        AdminStatus newStatus = request.status();

        validateOperationalStatus(newStatus);

        if (admin.getStatus() == newStatus) {
            throw new CustomException(HttpStatus.BAD_REQUEST, "현재 상태와 동일한 상태로 변경할 수 없습니다.");
        }

        validateLastActiveSuperAdminStatusChange(admin, newStatus);

        admin.changeStatus(newStatus);
    }

    @Transactional
    public void approveAdmin(Long adminId) {

        Admin admin = findAdmin(adminId);

        validatePendingStatus(admin);

        admin.approve();
    }

    @Transactional
    public void rejectAdmin(Long adminId, AdminRejectRequest request) {

        Admin admin = findAdmin(adminId);

        validatePendingStatus(admin);

        admin.reject(request.reason());
    }

    @Transactional
    public void deleteAdmin(Long adminId) {

        Admin admin = findAdmin(adminId);

        validateLastActiveSuperAdminDeletion(admin);

        adminRepository.delete(admin);
    }

    private Admin findAdmin(Long adminId) {
        return adminRepository.findById(adminId)
                .orElseThrow(() -> new CustomException(HttpStatus.NOT_FOUND, "관리자를 찾을 수 없습니다."));
    }

    private void validateEmailForUpdate(Long adminId, String email) {
        boolean duplicate = adminRepository.existsByEmailAndIdNot(email, adminId);
        if (duplicate) {
            throw new CustomException(HttpStatus.CONFLICT, "이미 사용 중인 이메일입니다.");
        }
    }

    private void validatePendingStatus(Admin admin) {
        if (admin.getStatus() != AdminStatus.PENDING) {
            throw new CustomException(HttpStatus.BAD_REQUEST, "승인 대기 상태의 관리자만 승인 또는 거부할 수 있습니다.");
        }
    }

    private void validateOperationalStatus(AdminStatus status) {
        if (status == AdminStatus.PENDING || status == AdminStatus.REJECTED) {
            throw new CustomException(HttpStatus.BAD_REQUEST, "승인대기와 거부 상태는 상태 변경 API로 설정할 수 없습니다.");
        }
    }

    // 마지막 활성 슈퍼 관리자의 역할을 다른 역할로 변경하지 못하게 한다.
    private void validateLastActiveSuperAdminRoleChange(Admin admin, AdminRole newRole) {
        boolean removesSuperAdminRole = admin.getRole() == AdminRole.SUPER_ADMIN && newRole != AdminRole.SUPER_ADMIN;

        if(!removesSuperAdminRole) {
            return;
        }

        long activeSuperAdminCount = adminRepository.countByRoleAndStatus(AdminRole.SUPER_ADMIN, AdminStatus.ACTIVE);

        if(admin.getStatus() == AdminStatus.ACTIVE && activeSuperAdminCount <= 1) {
            throw new CustomException(HttpStatus.BAD_REQUEST, "마지막 활성 슈퍼 관리자의 역할은 변경할 수 없습니다.");
        }
    }

    // 마지막 활성 슈퍼 관리자를 비활성 또는 정지 상태로 변경하지 못하게 한다.
    private void validateLastActiveSuperAdminStatusChange(Admin admin, AdminStatus newStatus) {
        boolean activeSuperAdmin = admin.getRole() == AdminRole.SUPER_ADMIN && admin.getStatus() == AdminStatus.ACTIVE;

        boolean losesActiveStatus = newStatus != AdminStatus.ACTIVE;

        if (!activeSuperAdmin || !losesActiveStatus) {
            return;
        }

        long activeSuperAdminCount = adminRepository.countByRoleAndStatus(AdminRole.SUPER_ADMIN, AdminStatus.ACTIVE);

        if (activeSuperAdminCount <= 1) {
            throw new CustomException(HttpStatus.BAD_REQUEST, "마지막 활성 슈퍼 관리자는 비활성화하거나 정지할 수 없습니다.");
        }
    }

    // 마지막 활성 슈퍼 관리자를 삭제하지 못하게 한다.
    private void validateLastActiveSuperAdminDeletion(Admin admin) {

        boolean activeSuperAdmin = admin.getRole() == AdminRole.SUPER_ADMIN && admin.getStatus() == AdminStatus.ACTIVE;

        if (!activeSuperAdmin) {
            return;
        }

        long activeSuperAdminCount = adminRepository.countByRoleAndStatus(AdminRole.SUPER_ADMIN, AdminStatus.ACTIVE);

        if (activeSuperAdminCount <= 1) {
            throw new CustomException(HttpStatus.BAD_REQUEST, "마지막 활성 슈퍼 관리자는 삭제할 수 없습니다.");
        }
    }

    // 정렬 기준(필드)과 방향(오름차순/내림차순) 문자열을 기반으로 JPA sort 객체를 생성
    private Sort createSort(String sortBy, String direction) {
        String property = resolveSortProperty(sortBy);

        Sort.Direction sortDirection = resolveSortDirection(direction);

        return Sort.by(sortDirection, property);
    }

    // 정렬 기준 검사
    private String resolveSortProperty(String sortBy) {
        if(sortBy == null || sortBy.isBlank()) {
            return "createdAt";
        }

        return switch (sortBy) {
            case "name" -> "name";
            case "email" -> "email";
            case "createdAt" -> "createdAt";

            default -> throw new CustomException(HttpStatus.BAD_REQUEST,"지원하지 않은 정렬 기준입니다.");
        };
    }

    // 정렬 방향 검사
    private Sort.Direction resolveSortDirection(String direction) {
        if (direction == null || direction.isBlank()) {
            return Sort.Direction.DESC;
        }

        return switch (direction.toLowerCase()) {
            case "asc" -> Sort.Direction.ASC;
            case "desc" -> Sort.Direction.DESC;

            default -> throw new CustomException(
                    HttpStatus.BAD_REQUEST,
                    "정렬 방향은 asc 또는 desc만 사용할 수 있습니다."
            );
        };
    }
}
