package com.example.customerproductsystem.admin.controller;
import com.example.customerproductsystem.admin.dto.AdminLoginRequest;
import com.example.customerproductsystem.admin.dto.AdminLoginResponse;
import com.example.customerproductsystem.admin.dto.AdminSignupRequest;
import com.example.customerproductsystem.admin.dto.AdminSignupResponse;
import com.example.customerproductsystem.admin.service.AdminAuthService;
import com.example.customerproductsystem.auth.LoginAdmin;
import com.example.customerproductsystem.auth.SessionConst;
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

    private static final int SESSION_TIMEOUT_SECONDS =
            60 * 60 * 24;

    private final AdminAuthService adminAuthService;

    @PostMapping("/signup")
    public ResponseEntity<AdminSignupResponse> signup(
            @Valid @RequestBody AdminSignupRequest request
    ) {
        AdminSignupResponse response = adminAuthService.signup(request);

        // 생성된 관리자 정보를 응답 헤더에 넣기 위한 코드
        URI location = URI.create("/admins/" + response.id());

        return ResponseEntity.created(location).body(response);
    }

    @PostMapping("/login")
    public ResponseEntity<AdminLoginResponse> login(
            @Valid @RequestBody AdminLoginRequest request,
            HttpServletRequest httpRequest
    ) {
        LoginAdmin loginAdmin = adminAuthService.login(request);

        // 기존 세션을 조회
        HttpSession oldSession = httpRequest.getSession(false);

        // 로그인 성공 시 새로운 세션 ID를 발급해서 세션 고정 공격의 위험을 조금이라도 줄이고자..
        if (oldSession != null) {
            oldSession.invalidate();
        }

        // true를 전달하면 내부적으로 새로운 세션을 생성합니다.
        HttpSession newSession = httpRequest.getSession(true);

        newSession.setAttribute(SessionConst.LOGIN_ADMIN, loginAdmin);

        // 세션의 최대 미사용 시간을 24시간으로 설정
        newSession.setMaxInactiveInterval(SESSION_TIMEOUT_SECONDS);

        AdminLoginResponse response = AdminLoginResponse.from(loginAdmin);

        return ResponseEntity.ok(response);
    }

    @PostMapping("/logout")
    public ResponseEntity<Void> logout(
            HttpServletRequest request
    ) {
        HttpSession session = request.getSession(false);

        if (session != null) {
            session.invalidate();
        }

        return ResponseEntity.noContent().build();
    }
}
