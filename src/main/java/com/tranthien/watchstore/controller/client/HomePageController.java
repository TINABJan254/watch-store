package com.tranthien.watchstore.controller.client;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.tranthien.watchstore.domain.Order;
import com.tranthien.watchstore.domain.Product;
import com.tranthien.watchstore.domain.User;
import com.tranthien.watchstore.domain.dto.RegisterDTO;
import com.tranthien.watchstore.service.OrderService;
import com.tranthien.watchstore.service.ProductService;
import com.tranthien.watchstore.service.UserService;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;

@Controller
public class HomePageController {

    private final UserService userService;
    private final PasswordEncoder passwordEncoder;
    private final ProductService productService;
    private final OrderService orderService;

    public HomePageController(UserService userService, PasswordEncoder passwordEncoder, ProductService productService, OrderService orderService){
        this.userService = userService;
        this.passwordEncoder = passwordEncoder;
        this.productService = productService;
        this.orderService = orderService;
    }

    @GetMapping("/")
    public String getHomePage(){
        return "client/homepage/index";
    }

    @GetMapping("/shop")
    public String getShoppingPage(Model model, @RequestParam("page") Optional<String> pageOptional){

        int page = 1;
        try {
            if (pageOptional.isPresent()) {
                // Convert from String to int
                page = Integer.parseInt(pageOptional.get());
            } else {
                // page = 1
            }
        } catch (Exception e) {
            // TODO: handle exception
        }

        Pageable pageable = PageRequest.of(page - 1, 2);
        Page<Product> productPage = this.productService.fetchProduct(pageable);
        List<Product> products = productPage.getContent();

        model.addAttribute("products", products);
        model.addAttribute("currentPage", page);
        model.addAttribute("totalPages", productPage.getTotalPages());
        
        return "client/homepage/shop";
    }

    @GetMapping("/login")
    public String getLoginPage(){
        return "client/auth/login";
    }

    @PostMapping("/login")
    public String handleLogin(){
        return "client/auth/login";
    }

    @GetMapping("/register")
    public String getRegisterPage(Model model){
        model.addAttribute("registerUser", new RegisterDTO());
        return "client/auth/register";
    }

    @PostMapping("/register")
    public String handleRegister(@ModelAttribute("registerUser") @Valid RegisterDTO registerDTO, BindingResult bindingResult){
        
        if (bindingResult.hasErrors()){
            return "client/auth/register";
        }

        User user = this.userService.registerDTOtoUser(registerDTO);

        String hashPassword = this.passwordEncoder.encode(user.getPassword());
        user.setPassword(hashPassword);
        user.setRole(this.userService.getRoleByName("USER"));

        // this.userService.handleSaveUser(user);
        return "redirect:/login";
    }

    @GetMapping("/order-history")
    public String getHistoryPage(Model model, HttpServletRequest request){
        HttpSession session = request.getSession(false);
        User user = this.userService.getUserById((long)session.getAttribute("id"));
        List<Order> orders = this.orderService.fetchOrderByUser(user);

        if (orders != null) {
            Collections.sort(orders, new Comparator<Order>() {
                @Override
                public int compare(Order o1, Order o2){
                    if (o1.getId() > o2.getId()) {
                        return -1;
                    }
                    return 1;
                }
            });
            model.addAttribute("orders", orders);
        }

        return "client/cart/order-history";
    }

    @GetMapping("access-denied")
    public String getDenyPage(){
        return "client/auth/deny";
    }
}
