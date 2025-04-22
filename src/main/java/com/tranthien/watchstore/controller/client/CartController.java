package com.tranthien.watchstore.controller.client;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import com.tranthien.watchstore.service.CartService;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;

@Controller
public class CartController {

    private final CartService cartService;

    public CartController(CartService cartService){
        this.cartService = cartService;
    }
    
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

    @PostMapping("/add-product-to-cart/{id}")
    public String addProductToCart(@PathVariable long id, HttpServletRequest request){
        HttpSession session  = request.getSession(false);

        String email = (String)session.getAttribute("email");
        this.cartService.handleAddProductToCart(email, id, session);

        String curUrl = request.getHeader("Referer"); //url before submit form

        return "redirect:" + curUrl;
    }
}
