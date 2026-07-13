package com.example.customerproductsystem.admin.dto;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

public record AdminUpdateRequest(

        @NotBlank(message = "이름은 필수입니다.")
        @Size(
                max = 50,
                message = "이름은 50자 이하여야 합니다."
        )
        String name,

        @NotBlank(message = "이메일은 필수입니다.")
        @Email(message = "올바른 이메일 형식이 아닙니다.")
        @Size(
                max = 100,
                message = "이메일은 100자 이하여야 합니다."
        )
        String email,

        @NotBlank(message = "전화번호는 필수입니다.")
        @Pattern(
                regexp = "^010-\\d{4}-\\d{4}$",
                message = "전화번호는 010-XXXX-XXXX 형식이어야 합니다."
        )
        String phoneNumber
) {
}
