package com.tranthien.watchstore.service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.tranthien.watchstore.domain.Cart;
import com.tranthien.watchstore.domain.CartDetail;
import com.tranthien.watchstore.domain.Order;
import com.tranthien.watchstore.domain.OrderDetail;
import com.tranthien.watchstore.domain.Product;
import com.tranthien.watchstore.domain.User;
import com.tranthien.watchstore.repository.CartDetailRepository;
import com.tranthien.watchstore.repository.CartRepository;
import com.tranthien.watchstore.repository.OrderDetailRepository;
import com.tranthien.watchstore.repository.OrderRepository;

import jakarta.servlet.http.HttpSession;

@Service
public class CartService {

    private final CartRepository cartRepository;
    private final UserService userService;
    private final ProductService productService;
    private final CartDetailRepository cartDetailRepository;
    private final OrderRepository orderRepository;
    private final OrderDetailRepository orderDetailRepository;

    public CartService(CartRepository cartRepository, UserService userService,
            ProductService productService,
            CartDetailRepository cartDetailRepository,
            OrderRepository orderRepository, 
            OrderDetailRepository orderDetailRepository) {
        this.cartRepository = cartRepository;
        this.userService = userService;
        this.productService = productService;
        this.cartDetailRepository = cartDetailRepository;
        this.orderRepository = orderRepository;
        this.orderDetailRepository = orderDetailRepository;
    }

    public Cart fetchCartByUser(User user){
        return this.cartRepository.findByUser(user);
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

                product.setQuantity(product.getQuantity() - 1);
                this.productService.handleSaveProduct(product);

                /*Console log*/
                System.out.println("--------------------------------------------------------------");
                System.out.printf(">>>>> Added product with name = '%s' to cart \n", product.getName());
                System.out.println("---------------------------------------------------------------");
                /*End console log*/

            }
        }
    }
    
    public void handleRemoveCartDetail(long cartDetailId, HttpSession session) {
        Optional<CartDetail> cartDetailOptional = this.cartDetailRepository.findById(cartDetailId);
        if (cartDetailOptional.isPresent()){
            CartDetail cartDetail = cartDetailOptional.get();
            Cart cart = cartDetail.getCart();

            Product product = cartDetail.getProduct();
            product.setQuantity(product.getQuantity() + cartDetail.getQuantity());
            this.productService.handleSaveProduct(product);

            this.cartDetailRepository.deleteById(cartDetailId);

            if (cart.getSum() > 1) {
                long s = cart.getSum() - 1;
                cart.setSum(s);
                session.setAttribute("sum", s);
                this.cartRepository.save(cart);
            } else if (cart.getSum() == 1){
                // this.cartRepository.delete(cart);
                cart.setSum(0);
                this.cartRepository.save(cart);
                session.setAttribute("sum", 0);
            }

            /*Console log*/
            System.out.println("--------------------------------------------------------------");
            System.out.printf(">>>>> Deleted product with name = '%s' from cart \n", product.getName());
            System.out.println("---------------------------------------------------------------");
            /*End console log*/

        }
    }

    public void handleUpdateCartBeforeCheckout(List<CartDetail> cartDetails){
        for (CartDetail cartDetail : cartDetails){
            Optional<CartDetail> cartDetaiOptional = this.cartDetailRepository.findById(cartDetail.getId());
            if (cartDetaiOptional.isPresent()){
                CartDetail curCartDetail = cartDetaiOptional.get();

                Product product = curCartDetail.getProduct();

                long r = curCartDetail.getQuantity() - cartDetail.getQuantity();
                if (-r > product.getQuantity()) {
                    curCartDetail.setQuantity(curCartDetail.getQuantity() + product.getQuantity());
                    product.setQuantity(0);
                    this.productService.handleSaveProduct(product);
                    this.cartDetailRepository.save(curCartDetail);
                    return;
                }

                product.setQuantity(product.getQuantity() + (curCartDetail.getQuantity() - cartDetail.getQuantity()));
                this.productService.handleSaveProduct(product);

                curCartDetail.setQuantity(cartDetail.getQuantity());
                this.cartDetailRepository.save(curCartDetail);
            }
        }
    }

    public void handlePlaceOrder(User user, HttpSession session, String receiverName, String receiverAddress, String receiverPhone) {
        user = this.userService.getUserById(user.getId());
        Cart cart = this.cartRepository.findByUser(user);
        if (cart != null) {
            List<CartDetail> cartDetails = cart.getCartDetails();

            if (cartDetails != null){
                Order order = new Order();
                order.setUser(user);
                order.setReceiverName(receiverName);
                order.setReceiverAddress(receiverAddress);
                order.setReceiverPhone(receiverPhone);
                order.setStatus("PENDING");

                order = this.orderRepository.save(order);

                for (CartDetail cartDetail : cartDetails){
                    OrderDetail orderDetail = new OrderDetail();
                    orderDetail.setOrder(order);
                    orderDetail.setPrice(cartDetail.getPrice());
                    orderDetail.setQuantity(cartDetail.getQuantity());
                    orderDetail.setProduct(cartDetail.getProduct());
                    this.orderDetailRepository.save(orderDetail);
                }

                /*Console log*/
                System.out.println("--------------------------------------------------------------");
                System.out.println(">>>>> Receiver: " + receiverName);
                System.out.println(">>>>> Address: " + receiverAddress);
                System.out.println(">>>>> Phone: " + receiverPhone);

                double totalPrice = 0;
                for (CartDetail cd : cartDetails) {
                    totalPrice += cd.getPrice() * cd.getQuantity();
                }
                System.out.println(">>>>> Total price: " + totalPrice);

                System.out.println(">>>>> List product: ");
                for (CartDetail cd : cartDetails) {
                    System.out.println("    Product: " + cd.getProduct().getName() + ", Price: " + cd.getPrice() + ", Quantity: " + cd.getQuantity());
                }
                System.out.println("---------------------------------------------------------------");
                /*End console log*/

                for (CartDetail cartDetail : cartDetails){
                    this.cartDetailRepository.deleteById(cartDetail.getId());
                }

                cart.setSum(0);
                cart.setCartDetails(null);
                this.cartRepository.save(cart);

                session.setAttribute("sum", 0);
            }
        }
    }

}
