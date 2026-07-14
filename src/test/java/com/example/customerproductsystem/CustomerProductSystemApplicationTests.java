package com.example.customerproductsystem;

import com.example.customerproductsystem.admin.repository.AdminRepository;
import com.example.customerproductsystem.customer.repository.CustomerRepository;
import com.example.customerproductsystem.order.repository.OrderRepository;
import com.example.customerproductsystem.product.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.transaction.annotation.Transactional;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

@SpringBootTest
@Transactional
@Sql("/test-data.sql")
class CustomerProductSystemApplicationTests {

    @Autowired
    private CustomerRepository customerRepository;

    @Autowired
    private AdminRepository adminRepository;

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private OrderRepository orderRepository;

    @Test
    void 테스트_데이터_넣기() {
        // 실제로 15명의 고객 데이터가 DB에 들어가 있는지 검증해봅니다.

        System.out.println("현재 저장된 고객 수: " + customerRepository.count());
        assertThat(customerRepository.count()).isEqualTo(15);

        System.out.println("현재 저장된 관리자 수: " + adminRepository.count());
        assertThat(adminRepository.count()).isEqualTo(2);

        System.out.println("현재 저장된 상품 수: " + productRepository.count());
        assertThat(productRepository.count()).isEqualTo(5);

        System.out.println("현재 저장된 주문 수: " + orderRepository.count());
        assertThat(orderRepository.count()).isEqualTo(5);
    }

}
