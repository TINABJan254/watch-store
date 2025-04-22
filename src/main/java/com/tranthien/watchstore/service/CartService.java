package com.tranthien.watchstore.service;

import org.springframework.stereotype.Service;

import com.tranthien.watchstore.domain.Cart;
import com.tranthien.watchstore.domain.CartDetail;
import com.tranthien.watchstore.domain.Product;
import com.tranthien.watchstore.domain.User;
import com.tranthien.watchstore.repository.CartDetailRepository;
import com.tranthien.watchstore.repository.CartRepository;

import jakarta.servlet.http.HttpSession;

@Service
public class CartService {

    private final CartRepository cartRepository;
    private final UserService userService;
    private final ProductService productService;
    private final CartDetailRepository cartDetailRepository;

    public CartService(CartRepository cartRepository, UserService userService,
            ProductService productService,
            CartDetailRepository cartDetailRepository) {
        this.cartRepository = cartRepository;
        this.userService = userService;
        this.productService = productService;
        this.cartDetailRepository = cartDetailRepository;
    }

    public void handleAddProductToCart(String email, long productId, HttpSession session) {
        User user = this.userService.getUserByEmail(email);

        if (user != null) {
            Cart cart = this.cartRepository.findByUser(user);

            if (cart == null) {
                Cart newCart = new Cart();
                newCart.setUser(user);
                newCart.setSum(0);

                cart = this.cartRepository.save(newCart);
            }

            Product product = this.productService.getProductById(productId);
            if (product != null) {
                CartDetail cartDetail = this.cartDetailRepository.findByCartAndProduct(cart, product);

                if (cartDetail == null) {
                    CartDetail newCartDetail = new CartDetail();
                    newCartDetail.setCart(cart);
                    newCartDetail.setProduct(product);
                    newCartDetail.setQuantity(1);
                    newCartDetail.setPrice(product.getPrice());

                    this.cartDetailRepository.save(newCartDetail);

                    //update cart sum
                    cart.setSum(cart.getSum() + 1);
                    cart = this.cartRepository.save(cart);
                    session.setAttribute("sum", cart.getSum());
                } else {
                    cartDetail.setQuantity(cartDetail.getQuantity() + 1);
                    this.cartDetailRepository.save(cartDetail);
                }
            }
        }
    }
}
