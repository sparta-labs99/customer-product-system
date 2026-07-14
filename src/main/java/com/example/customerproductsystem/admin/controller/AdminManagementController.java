package com.example.customerproductsystem.admin.controller;


import com.example.customerproductsystem.admin.dto.*;
import com.example.customerproductsystem.admin.service.AdminService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/admins")
public class AdminManagementController {

    private final AdminService adminService;

    // 관리자 상세 조회
    @GetMapping("/{adminId}")
    public ResponseEntity<AdminDetailResponse> getAdmin(@PathVariable Long adminId) {
        AdminDetailResponse response = adminService.getAdmin(adminId);

        return ResponseEntity.ok(response);
    }

    @GetMapping
    public ResponseEntity<PageResponse<AdminSummaryResponse>> getAdmins (
            @Valid @ModelAttribute AdminSearchCondition condition
    ) {
        PageResponse<AdminSummaryResponse> response = adminService.getAdmins(condition);

        return ResponseEntity.ok(response);
    }

    // 관리자 기본 정보 수정
    @PatchMapping("/{adminId}")
    public ResponseEntity<AdminUpdateResponse> updateAdmin(
            @PathVariable Long adminId,
            @Valid @RequestBody AdminUpdateRequest request
    ) {
        AdminUpdateResponse response = adminService.updateAdmin(adminId, request);

        return ResponseEntity.ok(response);
    }

    // 관리자 역할 변경
    @PatchMapping("/{adminId}/role")
    public ResponseEntity<Void> changeAdminRole(
            @PathVariable Long adminId,
            @Valid @RequestBody AdminRoleChangeRequest request
    ) {
        adminService.changeAdminRole(adminId, request);

        return ResponseEntity.noContent().build();
    }

    // 관리자 상태 변경
    @PatchMapping("/{adminId}/status")
    public ResponseEntity<Void> changeAdminStatus(
            @PathVariable Long adminId,
            @Valid @RequestBody AdminStatusChangeRequest request
    ) {
        adminService.changeAdminStatus(adminId,request);

        return ResponseEntity.noContent().build();
    }

    // 관리자 승인
    @PostMapping("/{adminId}/approval")
    public ResponseEntity<Void> approveAdmin(@PathVariable Long adminId) {
        adminService.approveAdmin(adminId);

        return ResponseEntity.noContent().build();
    }

    // 관리자 거부
    @PostMapping("/{adminId}/rejection")
    public ResponseEntity<Void> rejectAdmin(
            @PathVariable Long adminId,
            @Valid @RequestBody AdminRejectRequest request
    ) {
        adminService.rejectAdmin(adminId, request);

        return ResponseEntity.noContent().build();
    }

    // 관리자 삭제
    @DeleteMapping("/{adminId}")
    public ResponseEntity<Void> deleteAdmin(@PathVariable Long adminId) {
        adminService.deleteAdmin(adminId);

        return ResponseEntity.noContent().build();
    }
}
