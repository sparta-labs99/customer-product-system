package com.example.customerproductsystem.auth.interceptor;
import com.example.customerproductsystem.admin.entity.Admin;
import com.example.customerproductsystem.admin.entity.AdminStatus;
import com.example.customerproductsystem.admin.repository.AdminRepository;
import com.example.customerproductsystem.auth.LoginAdmin;
import com.example.customerproductsystem.auth.SessionConst;
import com.example.customerproductsystem.common.Error.CustomException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

@Component
@RequiredArgsConstructor
public class LoginCheckInterceptor implements HandlerInterceptor{

    private final AdminRepository adminRepository;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) {

        // false를 사요하면 세션이 없을 때 새로운 세션을 만들지 않는다.
        HttpSession session = request.getSession(false);

        if(session == null) {
            throw new CustomException(HttpStatus.UNAUTHORIZED, "로그인이 필요합니다. ");
        }

        Object sessionValue = session.getAttribute(SessionConst.LOGIN_ADMIN);

        // 세션은 있지만 로그인 정보가 없다면 정상적인 로그인 세션으로 볼 수 없다.
        if(!(sessionValue instanceof LoginAdmin loginAdmin)) {
            session.invalidate();
            throw new CustomException(HttpStatus.UNAUTHORIZED, "로그인이 필요합니다. ");
        }

        // 세션에 저장된 관리자 ID로 현재 DB 관리자 정보를 조회
        // 세션 생성 이후 관리자가 삭제됐을 수 있기 때문에 실제 DB의 존재 여부를 다시 확인
        Admin admin = adminRepository.findById(loginAdmin.id())
                .orElseThrow(() -> {
                    session.invalidate();
                    return new CustomException(HttpStatus.UNAUTHORIZED, "유효하지 않은 로그인 정보입니다. ");
                });

        // 로그인 이후에 관리자가 정지-비활성화-거부 상태로 변경됐을 가능성 확인. (ACTIVE가 아니면 기존 로그인 세션 무효)
        validateActiveStatus(admin, session);

        // 세션에 저장된 정보가 오래된 정보일 수 있기 때문에 현재 DB 정보를 기준으로 세션 갱신
        refreshLoginSession(admin, loginAdmin, session);

        return true;
    }

    private void validateActiveStatus(Admin admin, HttpSession session) {

        if(admin.getStatus() == AdminStatus.ACTIVE) {
            return;
        }

        // ACTIVE가 아니면 더 이상 사용할 수 없는 계정이므로 기존 세션도 함께 폐기
        session.invalidate();

        String message = switch (admin.getStatus()) {
            case PENDING -> "승인 대기 중인 관리자 입니다.";
            case REJECTED -> "가입 신청이 거부된 관리자입니다.";
            case SUSPENDED -> "정지된 관리자입니다.";
            case INACTIVE -> "비활성화된 관리자입니다.";
            case ACTIVE -> throw new IllegalStateException("ACTIVE 상태는 위에서 처리되었습니다.");
        };

        throw new CustomException(HttpStatus.FORBIDDEN, message);
    }

    private void refreshLoginSession(Admin admin, LoginAdmin loginAdmin, HttpSession session) {

        boolean emailChanged = !admin.getEmail().equals(loginAdmin.email());

        boolean roleChanged = admin.getRole() != loginAdmin.role();

        if(!emailChanged && !roleChanged) {
            return;
        }

        session.setAttribute(SessionConst.LOGIN_ADMIN, LoginAdmin.from(admin));
    }
}

