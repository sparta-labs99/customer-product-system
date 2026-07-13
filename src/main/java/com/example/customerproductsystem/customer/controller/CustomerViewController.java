package com.example.customerproductsystem.customer.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/view/customers")
public class CustomerViewController {

    @GetMapping
    public String customerListPage() {
        return "customer/list";
    }
}