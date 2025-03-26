package com.tranthien.watchstore.controller.admin;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.tranthien.watchstore.domain.Order;
import com.tranthien.watchstore.service.OrderService;

@Controller
public class OrderController {

    private final OrderService orderService;

    public OrderController(OrderService orderService){
        this.orderService = orderService;
    }
    
    @GetMapping("/admin/order")
    public String getOrderPage(Model model){
        List<Order> orders = this.orderService.fetchOrder();
        
        model.addAttribute("orders", orders);
        return "admin/order/show";
    }

    @GetMapping("/admin/order/{id}")
    public String getOrderDetailPage(Model model, @PathVariable long id){
        Order order = this.orderService.getOrderById(id).get();
        model.addAttribute("order", order);
        return "admin/order/detail";
    }

    @GetMapping("/admin/order/update/{id}")
    public String getUpdateOrderPage(Model model, @PathVariable long id){
        Order order = this.orderService.getOrderById(id).get();
        model.addAttribute("order", order);

        return "admin/order/update";
    }

    @PostMapping("/admin/order/update")
    public String updateOrder(@ModelAttribute("order") Order order){
        this.orderService.handleUpdateOrder(order);

        return "redirect:/admin/order";
    }

    @PostMapping("/admin/order/delete")
    public String deleteOrder(@RequestParam("id") Long id){
        this.orderService.handleDeleteOrderById(id);

        return "redirect:/admin/order";
    }
}
