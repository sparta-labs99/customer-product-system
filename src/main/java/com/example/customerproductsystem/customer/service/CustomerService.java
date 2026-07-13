package com.example.customerproductsystem.customer.service;

import com.example.customerproductsystem.common.config.PasswordEncoder;
import com.example.customerproductsystem.customer.dto.*;
import com.example.customerproductsystem.customer.entity.Customer;
import com.example.customerproductsystem.customer.entity.CustomerStatus;
import com.example.customerproductsystem.customer.repository.CustomerRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CustomerService {

    private final CustomerRepository customerRepository;
    private final PasswordEncoder passwordEncoder; // 추후 Security 적용 시 주석 해제

    /**
     * 고객 등록
     */
    @Transactional
    public CreateCustomerResponse createCustomer(CreateCustomerRequest request) {
        // 1. 이메일 중복 검증
        validateDuplicateEmail(request.getEmail());

        // 2. 비밀번호 암호화 (BCrypt)
        String encodedPassword = passwordEncoder.encode(request.getPassword());

        // 3. DTO 내부의 toEntity()를 활용하여 엔티티 생성 및 저장
        Customer customer = request.toEntity(encodedPassword);
        Customer savedCustomer = customerRepository.save(customer);

        // 4. 저장된 엔티티를 Response DTO로 변환하여 반환
        return new CreateCustomerResponse(savedCustomer);
    }

    /**
     * 고객 전체 조회  (페이징 처리)
     */
    @Transactional(readOnly = true)
    public Page<CreateCustomerResponse> getAllCustomers(CustomerSearchCondition condition, Pageable pageable) {        // 1. 페이지 번호와 사이즈를 받아 Pageable 객체 생성

        // condition 객체에서 status와 keyword를 꺼내 레포지토리의 동적 쿼리 호출
        Page<Customer> customers = customerRepository.findAllByCondition(
                condition.getStatus(),
                condition.getKeyword(),
                pageable
        );

        return customers.map(CreateCustomerResponse::new);
    }

    /**
     * 고객 단건 상세 조회
     */
    @Transactional(readOnly = true)
    public CreateCustomerResponse getCustomerDetail(Long id) {
        // 1. ID로 고객 엔티티 조회 (없으면 예외 발생)
        Customer customer = customerRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("존재하지 않는 고객입니다. ID: " + id));

        // 2. 조회된 엔티티를 CreateCustomerResponse DTO로 변환하여 반환
        return new CreateCustomerResponse(customer);
    }


    /**
     * 고객 정보 수정
     */
    @Transactional
    public UpdateCustomerResponse updateCustomer(Long id, UpdateCustomerRequest request) {
        // 1. 기존 고객 정보 조회
        Customer customer = customerRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("존재하지 않는 고객입니다. ID: " + id));

        // 2. 이메일 중복 검증 수행 (이메일이 변경될 때에만)
        if (!customer.getEmail().equals(request.getEmail())) {
            validateDuplicateEmail(request.getEmail());
        }

        // 3. 엔티티 내부 메서드를 통한 데이터 변경 (JPA 더티 체킹에 의해 자동 update 쿼리 유발)
        customer.updateCustomer(request);

        // 4. 수정 완료된 상태를 UpdateCustomerResponse DTO에 담아서 반환
        return new UpdateCustomerResponse(customer);
    }

    /**
     * 고객 상태(Status) 변경
     */
    @Transactional
    public UpdateCustomerResponse updateCustomerStatus(Long id, UpdateCustomerStatusRequest request) {
        // 1. 기존 고객 정보 조회
        Customer customer = customerRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("존재하지 않는 고객입니다. ID: " + id));

        // 2. 엔티티 내부 메서드를 통해 상태값 변경
        customer.updateStatus(request.getStatus());

        // 3. 변경 완료된 상태를 응답 DTO에 담아서 반환
        return new UpdateCustomerResponse(customer);
    }

    /**
     * 고객 삭제 (상태 변경)
     */
    @Transactional
    public void deleteCustomer(Long id) {
        // 1. 기존 고객 정보 조회
        Customer customer = customerRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("존재하지 않는 고객입니다. ID: " + id));

        // 2. 엔티티 내부 메서드를 통해 상태를 INACTIVE로 변경 (더티 체킹)
        customer.deleteCustomer();
    }


    /**
     * 이메일 중복 체크 검증 메서드
     */
    private void validateDuplicateEmail(String email) {
        if (customerRepository.existsByEmail(email)) {
            throw new IllegalStateException("이미 존재하는 이메일입니다.");
        }
    }
}