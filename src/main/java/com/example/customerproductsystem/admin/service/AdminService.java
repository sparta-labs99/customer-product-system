package com.example.customerproductsystem.admin.service;

import com.example.customerproductsystem.admin.dto.*;
import com.example.customerproductsystem.admin.entity.Admin;
import com.example.customerproductsystem.admin.entity.AdminRole;
import com.example.customerproductsystem.admin.entity.AdminStatus;
import com.example.customerproductsystem.admin.error.AdminException;
import com.example.customerproductsystem.admin.repository.AdminRepository;
import com.example.customerproductsystem.admin.repository.AdminSpecification;
import com.example.customerproductsystem.common.error.CustomException;
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

    @Transactional(readOnly = true)
    public PageResponse<AdminSummaryResponse> getAdmins(
            AdminSearchCondition condition
    ) {
        Sort sort = createSort(
                condition.getSortBy(),
                condition.getDirection()
        );

        Pageable pageable = PageRequest.of(
                condition.getPage() - 1,
                condition.getSize(),
                sort
        );

        Specification<Admin> spec = Specification.where(
                AdminSpecification.excludeSuperAdmin()
        ).and(
                AdminSpecification.keywordContains(condition.getKeyword())
        ).and(
                AdminSpecification.roleEquals(condition.getRole())
        ).and(
                AdminSpecification.statusEquals(condition.getStatus())
        );

        // 삭제 상태를 직접 조회하는 경우가 아니면 삭제된 관리자를 제외한다.
        if (condition.getStatus() != AdminStatus.DELETED) {
            spec = spec.and(AdminSpecification.notDeleted());
        }

        Page<AdminSummaryResponse> responsePage = adminRepository
                .findAll(spec, pageable)
                .map(AdminSummaryResponse::from);

        return PageResponse.from(responsePage);
    }

    @Transactional
    public AdminUpdateResponse updateAdmin(Long adminId, AdminUpdateRequest request) {

        Admin admin = findAdmin(adminId);

        admin.updateProfile(request.name(), request.phoneNumber());

        return AdminUpdateResponse.from(admin);
    }

    // PENDING, REJECTED는 승인·거부 API에서만 다룬다.
    @Transactional
    public void changeAdminRole(Long adminId, AdminRoleChangeRequest request) {

        Admin admin = findAdmin(adminId);

        if (admin.getRole() == request.role()) {
            throw new AdminException.SameRole();
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
            throw new AdminException.SameStatus();
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
    public void deleteAdmin(Long adminId, Long loginAdminId) {

        if (adminId.equals(loginAdminId)) {
            throw new CustomException(
                    HttpStatus.BAD_REQUEST,
                    "현재 로그인한 관리자 계정은 삭제할 수 없습니다."
            );
        }

        Admin admin = adminRepository
                .findByIdAndStatusNot(adminId, AdminStatus.DELETED)
                .orElseThrow(() -> new CustomException(
                        HttpStatus.NOT_FOUND,
                        "관리자를 찾을 수 없습니다."
                ));

        admin.delete();
    }

    // 삭제된 상태의 사용자는 찾지 못한다.
    private Admin findAdmin(Long adminId) {
        return adminRepository.findByIdAndStatusNot(adminId, AdminStatus.DELETED)
                .orElseThrow(() -> new AdminException.NotFound(adminId));
    }

    private void validatePendingStatus(Admin admin) {
        if (admin.getStatus() != AdminStatus.PENDING) {
            throw new AdminException.NotPending();
        }
    }

    private void validateOperationalStatus(AdminStatus status) {
        if (status == AdminStatus.PENDING || status == AdminStatus.REJECTED) {
            throw new AdminException.InvalidStatusChange();
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
            throw new AdminException.LastActiveSuperAdminRoleChange();
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
            throw new AdminException.LastSuperAdmin();
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
            throw new AdminException.LastSuperAdmin();
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

            default -> throw new AdminException.InvalidSort();
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

            default -> throw new AdminException.InvalidSortDirection();
        };
    }
}