package com.example.customerproductsystem.admin.dto;
import com.example.customerproductsystem.admin.entity.AdminRole;
import com.example.customerproductsystem.admin.entity.AdminStatus;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;

public class AdminSearchCondition {

    private String keyword;

    @Min(
            value = 1,
            message = "페이지 번호는 1 이상이어야 합니다."
    )
    private int page = 1;

    @Min(
            value = 1,
            message = "페이지 크기는 1 이상이어야 합니다."
    )
    @Max(
            value = 100,
            message = "페이지 크기는 100 이하여야 합니다."
    )
    private int size = 10;

    private String sortBy = "createdAt";

    private String direction = "desc";

    private AdminRole role;

    private AdminStatus status;

    public String getKeyword() {
        return keyword;
    }

    public int getPage() {
        return page;
    }

    public int getSize() {
        return size;
    }

    public String getSortBy() {
        return sortBy;
    }

    public String getDirection() {
        return direction;
    }

    public AdminRole getRole() {
        return role;
    }

    public AdminStatus getStatus() {
        return status;
    }
}
