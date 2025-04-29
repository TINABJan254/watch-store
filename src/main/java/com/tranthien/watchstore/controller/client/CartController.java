package com.tranthien.watchstore.controller.client;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import com.tranthien.watchstore.domain.Cart;
import com.tranthien.watchstore.domain.CartDetail;
import com.tranthien.watchstore.domain.User;
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
    public String getCart(Model model, HttpServletRequest request){
        HttpSession session = request.getSession();
        long id = (long) session.getAttribute("id");
        User user = new User();
        user.setId(id);

        Cart cart = this.cartService.fetchCartByUser(user);
        List<CartDetail> cartDetails = cart == null ? new ArrayList<>() : cart.getCartDetails();

        double totalPrice = 0;
        for (CartDetail cartDetail : cartDetails){
            totalPrice += cartDetail.getPrice() * cartDetail.getQuantity();
        }

        model.addAttribute("cartDetails", cartDetails);
        model.addAttribute("totalPrice", totalPrice);

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

    @PostMapping("/delete-cart-product/{id}")
    public String deleteProductInCart(@PathVariable long id, HttpServletRequest request) {
        HttpSession session = request.getSession(false);
        long cartDetailId = id;
        this.cartService.handleRemoveCartDetail(cartDetailId, session);
        return "redirect:/cart";
    }
}
