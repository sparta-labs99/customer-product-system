package com.example.customerproductsystem.auth.interceptor;

import com.example.customerproductsystem.admin.entity.Admin;
import com.example.customerproductsystem.admin.entity.AdminStatus;
import com.example.customerproductsystem.admin.error.AdminException;
import com.example.customerproductsystem.admin.repository.AdminRepository;
import com.example.customerproductsystem.auth.LoginAdmin;
import com.example.customerproductsystem.auth.SessionConst;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

@Component
@RequiredArgsConstructor
public class LoginCheckInterceptor implements HandlerInterceptor {

    private final AdminRepository adminRepository;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) {


        // false를 전달하면 기존 세션이 없는 경우 새로운 세션을 생성하지 않는다.
        HttpSession session = request.getSession(false);


        // 세션 자체가 없다면 로그인하지 않은 요청이다.
        if (session == null) {
            throw new AdminException.NotLogin();
        }

        Object sessionValue = session.getAttribute(SessionConst.LOGIN_ADMIN);

        // 세션은 존재하지만 로그인 관리자 정보가 없거나타입이 올바르지 않다면 유효하지 않은 세션이다.
        if (!(sessionValue instanceof LoginAdmin loginAdmin)) {
            session.invalidate();
            throw new AdminException.InvalidSession();
        }

        /* 세션에 저장된 관리자 ID를 기준으로현재 DB 관리자 정보를 다시 조회한다.
         * 로그인 이후 관리자가 삭제됐을 수도 있으므로 세션 정보만 신뢰하지 않는다. */
        Admin admin = adminRepository.findById(loginAdmin.id())
                        .orElseThrow(() -> { session.invalidate();
                            return new AdminException.InvalidSession();
                        });

        /* 로그인 이후 관리자 상태가 정지, 비활성, 거부 등으로 변경됐는지 검사한다. */
        validateActiveStatus(admin, session);

        /* DB의 이메일이나 역할이 변경된 경우 최신 정보로 세션을 갱신한다. */
        refreshLoginSession(admin, loginAdmin, session);

        return true;
    }

    private void validateActiveStatus(Admin admin, HttpSession session) {

        AdminStatus status = admin.getStatus();

        if (status == AdminStatus.ACTIVE) {
            return;
        }

        /* ACTIVE가 아닌 계정은 더 이상 현재 세션을 사용할 수 없으므로 세션을 폐기한다.*/
        session.invalidate();

        switch (status) {

            case PENDING ->
                    throw new AdminException.Pending();

            case REJECTED ->
                    throw new AdminException.Rejected();

            case SUSPENDED ->
                    throw new AdminException.Suspended();

            case INACTIVE ->
                    throw new AdminException.Inactive();

            case ACTIVE ->
                    throw new IllegalStateException(
                            "ACTIVE 상태는 위에서 처리되었습니다."
                    );
        }
    }

    private void refreshLoginSession(Admin admin, LoginAdmin loginAdmin, HttpSession session) {

        boolean emailChanged = !admin.getEmail().equals(loginAdmin.email());

        boolean roleChanged = admin.getRole() != loginAdmin.role();

        /* 이메일과 역할이 모두 동일하면 세션을 갱신할 필요가 없다.*/
        if (!emailChanged && !roleChanged) {
            return;
        }

        /*현재 DB 정보를 기준으로 로그인 세션을 갱신한다.*/
        session.setAttribute(
                SessionConst.LOGIN_ADMIN,
                LoginAdmin.from(admin)
        );
    }
}