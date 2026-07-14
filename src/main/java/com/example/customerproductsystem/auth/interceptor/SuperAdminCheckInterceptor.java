package com.example.customerproductsystem.auth.interceptor;

import com.example.customerproductsystem.admin.entity.AdminRole;
import com.example.customerproductsystem.auth.LoginAdmin;
import com.example.customerproductsystem.auth.SessionConst;
import com.example.customerproductsystem.common.error.CustomException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

@Component
public class SuperAdminCheckInterceptor implements HandlerInterceptor {

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) {

        HttpSession session = request.getSession(false);

        if(session == null) {
            throw new CustomException(HttpStatus.UNAUTHORIZED, "로그인이 필요합니다.");
        }

        Object sessionValue = session.getAttribute(SessionConst.LOGIN_ADMIN);

        if(!(sessionValue instanceof LoginAdmin loginAdmin)) {
            throw new CustomException(HttpStatus.UNAUTHORIZED, "로그인이 필요합니다. ");
        }

        if(loginAdmin.role() != AdminRole.SUPER_ADMIN) {
            throw new CustomException(HttpStatus.FORBIDDEN, "슈퍼 관리자 권한이 필요합니다.");
        }

        return true;
    }
}
