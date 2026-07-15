package com.example.customerproductsystem.customer.repository;

import com.example.customerproductsystem.customer.dto.CustomerStatusCount;
import com.example.customerproductsystem.customer.entity.Customer;
import com.example.customerproductsystem.customer.entity.CustomerStatus;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CustomerRepository extends JpaRepository<Customer, Long> {

    /*이메일 중복 여부 확인*/
    boolean existsByEmail(String email);

    /**
     * 상태 필터 및 키워드(이름/이메일) 동적 검색 쿼리
     * - :status가 null이면 앞 조건이 참이 되어 status 필터를 타지 않습니다.
     * - :keyword가 null이면 앞 조건이 참이 되어 keyword 필터를 타지 않습니다.
     * - 정렬(sortBy, direction)은 스프링 데이터 JPA가 pageable 객체 내부의 Sort 정보를 해석해
     *   쿼리 끝에 'ORDER BY ...' 구문을 자동으로 붙여줍니다.
     */
    @Query("SELECT c FROM Customer c WHERE " +
            "(:status IS NULL OR c.status = :status) AND " +
            "(:keyword IS NULL OR c.name LIKE %:keyword% OR c.email LIKE %:keyword%)")
    Page<Customer> findAllByCondition(
            @Param("status") CustomerStatus status,
            @Param("keyword") String keyword,
            Pageable pageable);

    long countByStatus(CustomerStatus status);

    //고객 상태 분포
    @Query("""
        SELECT new com.example.customerproductsystem.customer.dto.CustomerStatusCount(c.status, COUNT(c))
        FROM Customer c
        GROUP BY c.status
    """)
    List<CustomerStatusCount> getStatusDistribution();
}