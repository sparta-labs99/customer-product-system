package com.example.customerproductsystem.product.controller;

import com.example.customerproductsystem.admin.entity.AdminRole;
import com.example.customerproductsystem.auth.LoginAdmin;
import com.example.customerproductsystem.auth.SessionConst;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttribute;

@Controller
@RequestMapping("/view/products")
public class ProductViewController {

    @GetMapping("/list")
    public String productListPage() {
        return "product/list";
    }

    @GetMapping("/{id}")
    public String productDetailPage(@PathVariable Long id, Model model) {
        model.addAttribute("productId", id);
        return "product/detail";
    }

    @GetMapping("/new")
    public String productCreatePage(@SessionAttribute(name = SessionConst.LOGIN_ADMIN, required = false) LoginAdmin sessionAdmin) {
        if (sessionAdmin == null || (sessionAdmin.role() != AdminRole.SUPER_ADMIN && sessionAdmin.role() != AdminRole.OPERATION_ADMIN)) {
            return "error/401";
        }
        return "product/product";
    }
}