package com.example.customerproductsystem.auth.interceptor;

import com.example.customerproductsystem.admin.entity.AdminRole;
import com.example.customerproductsystem.admin.error.AdminException;
import com.example.customerproductsystem.auth.LoginAdmin;
import com.example.customerproductsystem.auth.SessionConst;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

@Component
public class OperationAdminCheckInterceptor implements HandlerInterceptor {

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) {
        if (!(request.getMethod().equals("POST") || request.getMethod().equals("DELETE"))) {
            return true;
        }

        HttpSession session = request.getSession(false);
        if (session == null) {
            throw new AdminException.NotLogin();
        }

        Object sessionValue = session.getAttribute(SessionConst.LOGIN_ADMIN);
        if (!(sessionValue instanceof LoginAdmin loginAdmin)) {
            throw new AdminException.InvalidSession();
        }

        if (loginAdmin.role() != AdminRole.SUPER_ADMIN && loginAdmin.role() != AdminRole.OPERATION_ADMIN) {
            throw new AdminException.OperationAdminRequired();
        }

        return true;
    }
}
