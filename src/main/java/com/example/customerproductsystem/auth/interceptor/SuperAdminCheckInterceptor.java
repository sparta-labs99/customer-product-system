package com.example.customerproductsystem.auth.interceptor;

import com.example.customerproductsystem.admin.entity.AdminRole;
import com.example.customerproductsystem.admin.error.AdminException;
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

        // /customers/** 경로로 요청이 왔을 때, PATCH와 DELETE가 아니라면(GET, POST, PUT 등) 슈퍼 관리자 검사 패스
        String requestURI = request.getRequestURI(); // 안전하고 확실하게 주소 가져오기
        String method = request.getMethod();
        if (requestURI.startsWith("/customers") && !(method.equals("PATCH") || method.equals("DELETE"))) {
            return true;
        }

        HttpSession session = request.getSession(false);

        if(session == null) {
            throw new AdminException.NotLogin();
        }

        Object sessionValue = session.getAttribute(SessionConst.LOGIN_ADMIN);

        if(!(sessionValue instanceof LoginAdmin loginAdmin)) {
            throw new AdminException.InvalidSession();
        }

        if(loginAdmin.role() != AdminRole.SUPER_ADMIN) {
            throw new AdminException.SuperAdminRequired();
        }

        return true;
    }
}
