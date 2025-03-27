package com.tranthien.watchstore.controller.client;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class CartController {
    
    @GetMapping("/cart")
    public String getCart(){
        return "client/cart/show";
    }

    @GetMapping("/checkout")
    public String getCheckoutPage(){
        return "client/cart/checkout";
    }

    @GetMapping("/order-success")
    public String getSuccessPage(){
        return "client/cart/order-success";
    }
}
