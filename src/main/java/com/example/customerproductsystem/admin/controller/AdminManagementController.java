package com.example.customerproductsystem.admin.controller;
import com.example.customerproductsystem.admin.dto.*;
import com.example.customerproductsystem.admin.service.AdminService;
import com.example.customerproductsystem.common.response.ApiResponse;
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
    public ResponseEntity<ApiResponse<AdminDetailResponse>> getAdmin(@PathVariable Long adminId) {
        AdminDetailResponse response = adminService.getAdmin(adminId);

        return ResponseEntity.ok(
                ApiResponse.success(response)
        );
    }

    @GetMapping
    public ResponseEntity<ApiResponse<PageResponse<AdminSummaryResponse>>> getAdmins(
            @Valid @ModelAttribute AdminSearchCondition condition
    ) {
        PageResponse<AdminSummaryResponse> response = adminService.getAdmins(condition);

        return ResponseEntity.ok(
                ApiResponse.success(response)
        );
    }

    // 관리자 기본 정보 수정
    @PatchMapping("/{adminId}")
    public ResponseEntity<ApiResponse<AdminUpdateResponse>> updateAdmin(
            @PathVariable Long adminId,
            @Valid @RequestBody AdminUpdateRequest request
    ) {
        AdminUpdateResponse response = adminService.updateAdmin(adminId, request);

        return ResponseEntity.ok(
                ApiResponse.success(
                        "관리자 정보가 수정되었습니다.",
                        response
                )
        );
    }

    // 관리자 역할 변경
    @PatchMapping("/{adminId}/role")
    public ResponseEntity<ApiResponse<Void>> changeAdminRole(
            @PathVariable Long adminId,
            @Valid @RequestBody AdminRoleChangeRequest request
    ) {
        adminService.changeAdminRole(adminId, request);

        return ResponseEntity.ok(
                ApiResponse.success(
                        "관리자 역할이 변경되었습니다.",
                        null
                )
        );
    }

    // 관리자 상태 변경
    @PatchMapping("/{adminId}/status")
    public ResponseEntity<ApiResponse<Void>> changeAdminStatus(
            @PathVariable Long adminId,
            @Valid @RequestBody AdminStatusChangeRequest request
    ) {
        adminService.changeAdminStatus(adminId, request);

        return ResponseEntity.ok(
                ApiResponse.success(
                        "관리자 상태가 변경되었습니다.",
                        null
                )
        );
    }

    // 관리자 승인
    @PostMapping("/{adminId}/approval")
    public ResponseEntity<ApiResponse<Void>> approveAdmin(@PathVariable Long adminId) {
        adminService.approveAdmin(adminId);

        return ResponseEntity.ok(
                ApiResponse.success(
                        "관리자 승인이 완료되었습니다.",
                        null
                )
        );
    }

    // 관리자 거부
    @PostMapping("/{adminId}/rejection")
    public ResponseEntity<ApiResponse<Void>> rejectAdmin(
            @PathVariable Long adminId,
            @Valid @RequestBody AdminRejectRequest request
    ) {
        adminService.rejectAdmin(adminId, request);

        return ResponseEntity.ok(
                ApiResponse.success(
                        "관리자 가입 신청이 거부되었습니다.",
                        null
                )
        );
    }

    // 관리자 삭제
    @DeleteMapping("/{adminId}")
    public ResponseEntity<ApiResponse<Void>> deleteAdmin(@PathVariable Long adminId) {
        adminService.deleteAdmin(adminId);

        return ResponseEntity.ok(
                ApiResponse.success(
                        "관리자가 삭제되었습니다.",
                        null
                )
        );
    }
}
