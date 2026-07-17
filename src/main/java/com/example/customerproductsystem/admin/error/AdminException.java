package com.example.customerproductsystem.admin.error;
import com.example.customerproductsystem.common.error.CustomException;
import com.example.customerproductsystem.common.error.ErrorCode;

public class AdminException {

    public static class NotFound extends CustomException {

        public NotFound(Long adminId) {
            super(
                    ErrorCode.ADMIN_NOT_FOUND,
                    "관리자를 찾을 수 없습니다. (ID: " + adminId + ")"
            );
        }
    }

    public static class DuplicateEmail extends CustomException {

        public DuplicateEmail() {
            super(
                    ErrorCode.ADMIN_DUPLICATE_EMAIL,
                    "이미 존재하는 이메일입니다."
            );
        }
    }

    public static class NotLogin extends CustomException {

        public NotLogin() {
            super(
                    ErrorCode.ADMIN_LOGIN_REQUIRED,
                    "로그인이 필요한 기능입니다."
            );
        }
    }

    public static class LoginFailed extends CustomException {

        public LoginFailed() {
            super(
                    ErrorCode.ADMIN_LOGIN_FAILED,
                    "이메일 또는 비밀번호가 일치하지 않습니다."
            );
        }
    }

    public static class Pending extends CustomException {

        public Pending() {
            super(
                    ErrorCode.ADMIN_PENDING,
                    "승인 대기 중인 관리자입니다."
            );
        }
    }

    public static class Rejected extends CustomException {

        public Rejected() {
            super(
                    ErrorCode.ADMIN_REJECTED,
                    "가입 신청이 거부된 관리자입니다."
            );
        }
    }

    public static class Suspended extends CustomException {

        public Suspended() {
            super(
                    ErrorCode.ADMIN_SUSPENDED,
                    "정지된 관리자입니다."
            );
        }
    }

    public static class Inactive extends CustomException {

        public Inactive() {
            super(
                    ErrorCode.ADMIN_INACTIVE,
                    "비활성화된 관리자입니다."
            );
        }
    }

    public static class Deleted extends CustomException {

        public Deleted() {
            super(
                    ErrorCode.ADMIN_DELETED,
                    "삭제된 관리자입니다."
            );
        }
    }

    public static class SuperAdminRequired extends CustomException {

        public SuperAdminRequired() {
            super(
                    ErrorCode.SUPER_ADMIN_REQUIRED,
                    "슈퍼 관리자 권한이 필요합니다."
            );
        }
    }

    public static class LastSuperAdmin extends CustomException {

        public LastSuperAdmin() {
            super(
                    ErrorCode.ADMIN_LAST_SUPER_ADMIN,
                    "마지막 활성 슈퍼 관리자는 변경하거나 삭제할 수 없습니다."
            );
        }
    }

    public static class CurrentPasswordMismatch extends CustomException {

        public CurrentPasswordMismatch() {
            super(
                    ErrorCode.ADMIN_CURRENT_PASSWORD_MISMATCH,
                    "현재 비밀번호가 일치하지 않습니다."
            );
        }
    }

    public static class SamePassword extends CustomException {

        public SamePassword() {
            super(
                    ErrorCode.ADMIN_SAME_PASSWORD,
                    "새 비밀번호는 기존 비밀번호와 같을 수 없습니다."
            );
        }
    }

    public static class InvalidSession extends CustomException {

        public InvalidSession() {
            super(
                    ErrorCode.ADMIN_INVALID_SESSION,
                    "유효하지 않은 로그인 정보입니다."
            );
        }
    }

    public static class SameRole extends CustomException {

        public SameRole() {
            super(
                    ErrorCode.ADMIN_SAME_ROLE,
                    "현재 역할과 동일한 역할로 변경할 수 없습니다."
            );
        }
    }

    public static class SameStatus extends CustomException {

        public SameStatus() {
            super(
                    ErrorCode.ADMIN_SAME_STATUS,
                    "현재 상태와 동일한 상태로 변경할 수 없습니다."
            );
        }
    }

    public static class NotPending extends CustomException {
        public NotPending() {
            super(
                    ErrorCode.ADMIN_NOT_PENDING,
                    "승인 대기 상태의 관리자만 승인 또는 거부할 수 있습니다."
            );
        }
    }

    public static class InvalidStatusChange extends CustomException {
        public InvalidStatusChange() {
            super(
                    ErrorCode.ADMIN_INVALID_STATUS_CHANGE,
                    "승인 대기와 거부 상태는 상태 변경 API로 설정할 수 없습니다."
            );
        }
    }

    public static class LastActiveSuperAdminRoleChange extends CustomException {
        public LastActiveSuperAdminRoleChange() {
            super(
                    ErrorCode.ADMIN_LAST_ACTIVE_SUPER_ADMIN_ROLE_CHANGE,
                    "마지막 활성 슈퍼 관리자의 역할은 변경할 수 없습니다."
            );
        }
    }

    public static class InvalidSort extends CustomException {
        public InvalidSort() {
            super(
                    ErrorCode.ADMIN_INVALID_SORT,
                    "지원하지 않는 정렬 기준입니다."
            );
        }
    }

    public static class InvalidSortDirection extends CustomException {
        public InvalidSortDirection() {
            super(
                    ErrorCode.ADMIN_INVALID_SORT_DIRECTION,
                    "정렬 방향은 asc 또는 desc만 사용할 수 있습니다."
            );
        }
    }
}