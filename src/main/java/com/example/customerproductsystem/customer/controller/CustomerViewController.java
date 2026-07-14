package com.example.customerproductsystem.customer.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.ui.Model;

@Controller
@RequestMapping("/view/customers")
public class CustomerViewController {

    @GetMapping
    public String customerListPage() {
        return "customer/list";
    }

    @GetMapping("/{id}")
    public String customerDetailPage(@PathVariable Long id, Model model) {
        model.addAttribute("customerId", id); // JS에서 사용할 수 있게 ID만 넘겨줌
        return "customer/detail";
    }
}