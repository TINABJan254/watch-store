package com.tranthien.watchstore.controller.client;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomePageController {

    @GetMapping("/")
    public String getHomePage(){
        return "client/homepage/index";
    }

    @GetMapping("/shop")
    public String getShoppingPage(){
        return "client/homepage/shop";
    }

    @GetMapping("/login")
    public String getLoginPage(){
        return "client/auth/login";
    }

    @GetMapping("/register")
    public String getRegisterPage(){
        return "client/auth/register";
    }

    @GetMapping("/order-history")
    public String getHistoryPage(){
        return "client/cart/order-history";
    }
}
