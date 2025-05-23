package com.tranthien.watchstore.controller.admin;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.tranthien.watchstore.domain.Product;
import com.tranthien.watchstore.repository.ProductRepository;
import com.tranthien.watchstore.service.OrderService;
import com.tranthien.watchstore.service.ProductService;
import com.tranthien.watchstore.service.UserService;

@Controller
public class DashboardController {

    private final UserService userService;
    private final ProductService productService;
    private final OrderService orderService;
    private final ProductRepository productRepository;

    public DashboardController(UserService userService, ProductService productService, OrderService orderService, ProductRepository productRepository) {
        this.userService = userService;
        this.productService = productService;
        this.orderService = orderService;
        this.productRepository = productRepository;
    }

    @GetMapping("/admin")
    public String index(Model model,  @RequestParam("top") Optional<Integer> topOptional) {

        model.addAttribute("countUsers", this.userService.countUsers());
        model.addAttribute("countProducts", this.productService.countProducts());
        model.addAttribute("countOrders", this.orderService.countOrders());

        int top = 5;
        top = topOptional.orElse(5); // default top = 5
        Pageable pageable = PageRequest.of(0, top,
        Sort.by(
            Sort.Order.desc("sold"),
            Sort.Order.asc("name")
        ));
        Page<Product> productPage = productRepository.findAll(pageable);
        List<Product> topProducts = productPage.getContent();

        model.addAttribute("topProducts", topProducts);

        /* Console log*/
        System.out.println("----------------------------------------------");
        System.out.println(">>>>> Total users: " + this.userService.countUsers());
        System.out.println(">>>>> Total products: " + this.productService.countProducts());
        System.out.println(">>>>> Total orders: " + this.orderService.countOrders());
        System.out.println("----------------------------------------------");
        /* End console log*/

        return "admin/dashboard/show";
    }
}
