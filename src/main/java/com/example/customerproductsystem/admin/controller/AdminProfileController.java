package com.example.customerproductsystem.admin.controller;

import com.example.customerproductsystem.admin.dto.MyProfileResponse;
import com.example.customerproductsystem.admin.dto.MyProfileUpdateRequest;
import com.example.customerproductsystem.admin.dto.PasswordChangeRequest;
import com.example.customerproductsystem.admin.service.AdminProfileService;
import com.example.customerproductsystem.auth.LoginAdmin;
import com.example.customerproductsystem.auth.SessionConst;
import com.example.customerproductsystem.common.response.ApiResponse;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/admins/me")
public class AdminProfileController {

    private final AdminProfileService adminProfileService;

    @GetMapping
    public ResponseEntity<ApiResponse<MyProfileResponse>> getMyProfile(HttpServletRequest request) {

        LoginAdmin loginAdmin = getLoginAdmin(request);

        MyProfileResponse response = adminProfileService.getMyProfile(loginAdmin.id());

        return ResponseEntity.ok(
                ApiResponse.success(response)
        );
    }

    @PatchMapping
    public ResponseEntity<ApiResponse<MyProfileResponse>> updateMyProfile(
            HttpServletRequest request,
            @Valid @RequestBody MyProfileUpdateRequest updateRequest
    ) {

        LoginAdmin loginAdmin = getLoginAdmin(request);

        MyProfileResponse response = adminProfileService.updateMyProfile(loginAdmin.id(), updateRequest);

        updateLoginSession(request, loginAdmin, response);

        return ResponseEntity.ok(
                ApiResponse.success(
                        "프로필 정보가 수정되었습니다.",
                        response
                )
        );
    }

    @PatchMapping("/password")
    public ResponseEntity<ApiResponse<Void>> changedPassword(
            HttpServletRequest request,
            @Valid @RequestBody PasswordChangeRequest passwordRequest
    ) {
        LoginAdmin loginAdmin = getLoginAdmin(request);

        adminProfileService.changedPassword(loginAdmin.id(), passwordRequest);

        return ResponseEntity.ok(
                ApiResponse.success(
                        "비밀번호가 변경되었습니다.",
                        null
                )
        );
    }

    // 현재 세션에서 로그인 관리자 정보를 가져온다.
    private LoginAdmin getLoginAdmin(HttpServletRequest request) {
        HttpSession session = request.getSession(false);

        return (LoginAdmin) session.getAttribute(SessionConst.LOGIN_ADMIN);
    }

    // 프로필 수정으로 이메일이 바뀌면 기존 세션 정보도 갱신
    private void updateLoginSession(
            HttpServletRequest request,
            LoginAdmin loginAdmin,
            MyProfileResponse response
    ) {
        if(loginAdmin.email().equals(response.email())) {
            return;
        }

        HttpSession session = request.getSession(false);

        LoginAdmin update = new LoginAdmin(loginAdmin.id(), response.email(), loginAdmin.role());

        session.setAttribute(SessionConst.LOGIN_ADMIN, update);
    }
}