package com.example.customerproductsystem.admin.repository;
import com.example.customerproductsystem.admin.entity.Admin;
import com.example.customerproductsystem.admin.entity.AdminRole;
import com.example.customerproductsystem.admin.entity.AdminStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import java.util.Optional;

public interface AdminRepository extends JpaRepository<Admin, Long>, JpaSpecificationExecutor<Admin> {

    boolean existsByEmail(String email);

    boolean existsByEmailAndIdNot(String email, Long adminId);

    Optional<Admin> findByEmail(String email);

    boolean existsByRole(AdminRole role);

    long countByRoleAndStatus(AdminRole role, AdminStatus status);
}
