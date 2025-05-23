package com.tranthien.watchstore.controller.admin;

import java.util.ArrayList;
import java.util.Comparator;
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
import com.tranthien.watchstore.domain.OrderDetail;
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
        List<Order> orders = new ArrayList<>(orderPage.getContent());
        
        orders.sort(new Comparator<Order>() {
            @Override
            public int compare(Order o1, Order o2) {
                return o2.getCreateTime().compareTo(o1.getCreateTime());
            }
        });

        model.addAttribute("orders", orders);
        model.addAttribute("currentPage", page);
        model.addAttribute("totalPages", orderPage.getTotalPages());
        model.addAttribute("limit", limit);

        /*Console log*/
        System.out.println("-----------------------------------------------------------");
        System.out.println(">>>>> Limit: " + limit + ", page: " + page);
        for (Order od : orders) {
            System.out.println(">>>>> ID: " + od.getId() + ", User: " + od.getUser().getFullName() + ", Total price: " + 
                od.getTotalPrice() + ", Status: " + od.getStatus());
        }
        System.out.println("-----------------------------------------------------------");
        /*End console log*/

        return "admin/order/show";
    }

    @GetMapping("/admin/order/{id}")
    public String getOrderDetailPage(Model model, @PathVariable long id){
        Order order = this.orderService.getOrderById(id).get();
        model.addAttribute("order", order);

        /*Console log*/
        System.out.println("----------------------------------------------------");
        System.out.println(">>>>> ID: " + id);
        System.out.println(">>>>> Name: " + order.getReceiverName());
        System.out.println(">>>>> Address: " + order.getReceiverAddress());
        System.out.println(">>>>> Phone number: " + order.getReceiverPhone());
        System.out.println(">>>>> Total price: " + order.getTotalPrice());
        System.out.println("Order detail:");
        for (OrderDetail od : order.getOrderDetails()) {
            System.out.println(">>>>> Product: " + od.getProduct().getName() + ",  Quantity: " + od.getQuantity());
        }
        System.out.println("----------------------------------------------------");
        /*End console log*/

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

        /*Console log*/
        System.out.println("----------------------------------------------------");
        System.out.println(">>>>> Delete order with ID = " + id);
        System.out.println("----------------------------------------------------");
        /*End console log*/

        return "redirect:/admin/order";
    }
}
