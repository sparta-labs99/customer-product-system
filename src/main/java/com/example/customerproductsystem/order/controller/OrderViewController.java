package com.example.customerproductsystem.order.controller;

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
@RequestMapping("/view/orders")
public class OrderViewController {

    @GetMapping
    public String orderListPage() {
        return "order/list";
    }

    @GetMapping("/{id}")
    public String orderDetailPage(@PathVariable Long id, Model model) {
        model.addAttribute("orderId", id);
        return "order/detail";
    }

    @GetMapping("/new")
    public String orderCreatePage(@SessionAttribute(name = SessionConst.LOGIN_ADMIN, required = false) LoginAdmin sessionAdmin) {
        if (sessionAdmin == null || sessionAdmin.role() != AdminRole.CS_ADMIN) {
            return "error/401";
        }
        return "order/order";
    }
}
