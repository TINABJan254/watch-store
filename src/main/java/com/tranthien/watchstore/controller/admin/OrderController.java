package com.tranthien.watchstore.controller.admin;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
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
    public String getOrderPage(Model model, 
        @RequestParam("page") Optional<String> pageOptional,
        @RequestParam("limit") Optional<String> limitOptional){

        int page = 1;
        int limit = 10;
        try {
            if (pageOptional.isPresent()) {
                // Convert from String to int
                page = Integer.parseInt(pageOptional.get());
            } 

            if (limitOptional.isPresent()) {
                limit = Integer.parseInt(limitOptional.get());
                if (limit > 20) {
                    limit = 20;
                }
            }
        } catch (Exception e) {
            // TODO: handle exception
        }
        
        Pageable pageable = PageRequest.of(page - 1, limit);

        Page<Order> orderPage = this.orderService.fetchOrder(pageable);
        List<Order> orders = orderPage.getContent();
        
        model.addAttribute("orders", orders);
        model.addAttribute("currentPage", page);
        model.addAttribute("totalPages", orderPage.getTotalPages());
        model.addAttribute("limit", limit);

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
