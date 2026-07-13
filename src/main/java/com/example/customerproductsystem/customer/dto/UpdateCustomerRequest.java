package com.example.customerproductsystem.customer.dto;

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
public class UpdateCustomerRequest {

    @NotBlank(message = "고객 이름은 필수 항목입니다.")
    @Size(max = 50, message = "이름은 최대 {max}자까지 입력 가능합니다.")
    private String name;

    @NotBlank(message = "이메일은 필수 항목입니다.")
    @Email(message = "올바른 이메일 형식이 아닙니다.")
    @Size(max = 100, message = "이메일은 최대 {max}자까지 입력 가능합니다.")
    private String email;

    @NotBlank(message = "전화번호는 필수 항목입니다.")
    @Pattern(regexp = "^010-\\d{4}-\\d{4}$", message = "전화번호 형식은 010-XXXX-XXXX 형태여야 합니다.")
    private String phoneNumber;

    @Builder
    public UpdateCustomerRequest(String name, String email, String phoneNumber) {
        this.name = name;
        this.email = email;
        this.phoneNumber = phoneNumber;
    }
}