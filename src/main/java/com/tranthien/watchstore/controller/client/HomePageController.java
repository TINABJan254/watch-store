package com.tranthien.watchstore.controller.client;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import com.tranthien.watchstore.domain.Order;
import com.tranthien.watchstore.domain.Product;
import com.tranthien.watchstore.domain.Product_;
import com.tranthien.watchstore.domain.User;
import com.tranthien.watchstore.domain.dto.ProductCriteriaDTO;
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
    public String getShoppingPage(Model model, ProductCriteriaDTO productCriteriaDTO, HttpServletRequest request){

        int page = 1;
        int limit = 9;
        try {
            if (productCriteriaDTO.getPage().isPresent()) {
                // Convert from String to int
                page = Integer.parseInt(productCriteriaDTO.getPage().get());
            }

            if (productCriteriaDTO.getLimit().isPresent()) {
                limit = Integer.parseInt(productCriteriaDTO.getLimit().get());
            }
        } catch (Exception e) {
            // TODO: handle exception
        }

        // Sort
        Pageable pageable = PageRequest.of(page - 1, limit);
        if (productCriteriaDTO.getSort() != null && productCriteriaDTO.getSort().isPresent()) {
            String sort = productCriteriaDTO.getSort().get();
            if (sort.equals("asc")) {
                pageable = PageRequest.of(page - 1, limit, Sort.by(Product_.PRICE).ascending());
            } else if (sort.equals("desc")) {
                pageable = PageRequest.of(page - 1, limit, Sort.by(Product_.PRICE).descending());
            } else if (sort.equals("none")) {
                pageable = PageRequest.of(page - 1, limit);
            }
        }

        // double minPrice = minPriceOptional.isPresent() ? Double.parseDouble(minPriceOptional.get()) : 0;
        // double maxPrice = maxPriceOptional.isPresent() ? Double.parseDouble(maxPriceOptional.get()) : 0;
        // String factory = factoryOptional.isPresent() ? factoryOptional.get() : "";
        // String type = typeOptional.isPresent() ? typeOptional.get() : "";
        // List<String> factory = Arrays.asList(factoryOptional.get().split(","));
        // List<String> type = Arrays.asList(typeOptional.get().split(","));
        // String priceRange = priceRangeOptional.isPresent() ? priceRangeOptional.get() : "";
        // List<String> priceRange = Arrays.asList(priceRangeOptional.isPresent() ? priceRangeOptional.get().split(",") : "".split(","));

        // Page<Product> productPage = this.productService.fetchProductByMinPrice(pageable, minPrice);
        // Page<Product> productPage = this.productService.fetchProductByMaxPrice(pageable, maxPrice);
        // Page<Product> productPage = this.productService.fetchProductByFactory(pageable, factory);
        // Page<Product> productPage = this.productService.fetchProductByMultiFactory(pageable, factory);
        // Page<Product> productPage = this.productService.fetchProductByMultiType(pageable, type);
        // Page<Product> productPage = this.productService.fetchProductByMultiPriceRange(pageable, priceRange);

        Page<Product> productPage = this.productService.fetchProductWithSpecs(pageable, productCriteriaDTO);

        List<Product> products = productPage.getContent().size() > 0 ? productPage.getContent() : new ArrayList<>();

        String queryString = request.getQueryString();
        if (queryString != null && !queryString.isBlank()) {
            // remove page param
            queryString = queryString.replace("&page=" + page, "");
            queryString = queryString.replace("limit=" + limit, "");
        }


        model.addAttribute("products", products);
        model.addAttribute("currentPage", page);
        model.addAttribute("totalPages", productPage.getTotalPages());
        model.addAttribute("limit", limit);
        model.addAttribute("queryString", queryString);

        /* Console log */
        System.out.println(">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>");
        System.out.println(">>>> Limit: " + limit + " " + page);
        for (Product p : products) {
            System.out.println(">>>> ID:" + p.getId() + ",  Name:" + p.getName());
        }
        System.out.println(">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>");
        /* End console log */

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
