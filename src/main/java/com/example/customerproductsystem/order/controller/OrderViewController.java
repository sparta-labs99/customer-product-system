package com.example.customerproductsystem.order.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.ui.Model;

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
    public String orderCreatePage() {
        return "order/order";
    }
}
