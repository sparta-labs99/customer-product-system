package com.example.customerproductsystem.admin.controller;
import com.example.customerproductsystem.admin.dto.AdminLoginRequest;
import com.example.customerproductsystem.admin.dto.AdminLoginResponse;
import com.example.customerproductsystem.admin.dto.AdminSignupRequest;
import com.example.customerproductsystem.admin.dto.AdminSignupResponse;
import com.example.customerproductsystem.admin.service.AdminAuthService;
import com.example.customerproductsystem.auth.LoginAdmin;
import com.example.customerproductsystem.auth.SessionConst;
import com.example.customerproductsystem.common.response.ApiResponse;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.annotation.*;
import java.net.URI;

@RestController
@RequiredArgsConstructor
public class AdminAuthController {

    private static final int SESSION_TIMEOUT_SECONDS = 60 * 60 * 24;

    private final AdminAuthService adminAuthService;
    @PostMapping("/signup")
    public ResponseEntity<ApiResponse<AdminSignupResponse>> signup(
            @Valid @RequestBody AdminSignupRequest request
    ) {
        AdminSignupResponse response = adminAuthService.signup(request);

        URI location = URI.create("/admins/" + response.id());

        return ResponseEntity.created(location)
                .body(ApiResponse.success("관리자 가입 신청이 완료되었습니다.", response));
    }


    @PostMapping("/login")
    public ResponseEntity<ApiResponse<AdminLoginResponse>> login(
            @Valid @RequestBody AdminLoginRequest request,
            HttpServletRequest httpRequest
    ) {
        LoginAdmin loginAdmin =
                adminAuthService.login(request);

        HttpSession oldSession =
                httpRequest.getSession(false);

        if (oldSession != null) {
            oldSession.invalidate();
        }

        HttpSession newSession =
                httpRequest.getSession(true);

        newSession.setAttribute(
                SessionConst.LOGIN_ADMIN,
                loginAdmin
        );

        newSession.setMaxInactiveInterval(
                SESSION_TIMEOUT_SECONDS
        );

        AdminLoginResponse response =
                AdminLoginResponse.from(loginAdmin);

        return ResponseEntity.ok(
                ApiResponse.success(
                        "로그인에 성공했습니다.",
                        response
                )
        );
    }


    @PostMapping("/logout")
    public ResponseEntity<ApiResponse<Void>> logout(
            HttpServletRequest request
    ) {
        HttpSession session =
                request.getSession(false);

        if (session != null) {
            session.invalidate();
        }

        return ResponseEntity.ok(
                ApiResponse.success(
                        "로그아웃되었습니다.",
                        null
                )
        );
    }
}
