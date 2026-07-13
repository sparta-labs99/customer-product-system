package com.example.customerproductsystem.customer.dto;

import com.example.customerproductsystem.customer.entity.Customer;
import com.example.customerproductsystem.customer.entity.CustomerStatus;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class CreateCustomerRequest {

    @NotBlank(message = "고객 이름은 필수 항목입니다.")
    private String name;

    @NotBlank(message = "이메일은 필수 항목입니다.")
    @Email(message = "올바른 이메일 형식이 아닙니다.")
    private String email;

    @NotBlank(message = "비밀번호는 필수 항목입니다.")
    @Size(min = 8, message = "비밀번호는 {min}자 이상 입력해야 합니다.")
    private String password;

    @NotBlank(message = "전화번호는 필수 항목입니다.")
    @Pattern(regexp = "^010-\\d{4}-\\d{4}$", message = "전화번호 형식은 010-XXXX-XXXX 형태여야 합니다.")
    private String phoneNumber;

    // DTO를 Customer 엔티티로 변환
    public Customer toEntity(String encodedPassword) {
        return Customer.builder()
                .name(this.name)
                .email(this.email)
                .password(encodedPassword)   // 암호화된 비밀번호 주입
                .phoneNumber(this.phoneNumber)
                .status(CustomerStatus.ACTIVE) // 초기 상태값 주입
                .build();
    }
}