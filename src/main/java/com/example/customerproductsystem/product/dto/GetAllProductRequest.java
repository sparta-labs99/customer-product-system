package com.example.customerproductsystem.product.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Getter;

@Getter
public class GetAllProductRequest {

    private int size;

    @Size(max = 100)
    private String keyword;

    @NotBlank
    @Size(max = 50)
    private String direction;

    @NotBlank
    @Size(max = 50)
    private String status;

}
