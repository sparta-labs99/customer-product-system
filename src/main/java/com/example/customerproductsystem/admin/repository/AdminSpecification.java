package com.example.customerproductsystem.admin.repository;

import com.example.customerproductsystem.admin.entity.Admin;
import com.example.customerproductsystem.admin.entity.AdminRole;
import com.example.customerproductsystem.admin.entity.AdminStatus;
import org.springframework.data.jpa.domain.Specification;

public class AdminSpecification {

    private AdminSpecification() {}

    // 이름 또는 이메일에 검색어가 포함된 관리자를 검색
    public static Specification<Admin> keywordContains(String keyword) {
        return (root, query, criteriaBuilder) -> {
            if(keyword == null || keyword.isBlank()) {
                return null;
            }

            String pattern = "%" + keyword.trim().toLowerCase() + "%";

            return criteriaBuilder.or(
                    criteriaBuilder.like(
                            criteriaBuilder.lower(
                                    root.get("name")
                            ),
                            pattern
                    ),
                    criteriaBuilder.like(
                            criteriaBuilder.lower(root.get("email")), pattern
                    )
            );
        };
    }

    // 관리자 역할로 필터링
    public static Specification<Admin> roleEquals(AdminRole role) {
        return (root, query, criteriaBuilder) -> {
            if (role == null) {
                return null;
            }
            return criteriaBuilder.equal(root.get("role"), role);
        };
    }

    // 관리자 상태로 필터링
    public static Specification<Admin> statusEquals(AdminStatus status) {
        return ((root, query, criteriaBuilder) -> {
            if(status == null) {
                return null;
            }

            return criteriaBuilder.equal(root.get("status"), status);
        });
    }
}
