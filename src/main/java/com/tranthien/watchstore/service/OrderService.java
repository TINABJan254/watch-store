package com.tranthien.watchstore.service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.tranthien.watchstore.domain.Order;
import com.tranthien.watchstore.domain.OrderDetail;
import com.tranthien.watchstore.repository.OrderDetailRepository;
import com.tranthien.watchstore.repository.OrderRepository;

@Service
public class OrderService {
    
    private final OrderRepository orderRepository;
    private final OrderDetailRepository orderDetailRepository;

    public OrderService(OrderRepository orderRepository, OrderDetailRepository orderDetailRepository){
        this.orderRepository = orderRepository;
        this.orderDetailRepository = orderDetailRepository;
    }

    public List<Order> fetchOrder(){
        return this.orderRepository.findAll();
    }

    public Order handleSaveOrder(Order order){
        return this.orderRepository.save(order);
    }

    public OrderDetail handleSaveOrderDetail(OrderDetail orderDetail){
        return this.orderDetailRepository.save(orderDetail);
    }

    public Optional<Order> getOrderById(long id){
        return this.orderRepository.findById(id);
    }

    public void handleUpdateOrder(Order order){
        Optional<Order> orderOptional = this.orderRepository.findById(order.getId());
        if (orderOptional.isPresent()){
            Order currentOrder = orderOptional.get();
            currentOrder.setStatus(order.getStatus());
            this.orderRepository.save(currentOrder);
        }
    }

    public void handleDeleteOrderById(long id){
        Optional<Order> orderOptional = this.orderRepository.findById(id);
        if (orderOptional.isPresent()) {
            List<OrderDetail> orderDetails = orderOptional.get().getOrderDetails();

            if (orderDetails != null) {
                for (OrderDetail orderDetail : orderDetails){
                    this.orderDetailRepository.delete(orderDetail);
                }
            }
            
            this.orderRepository.deleteById(id);
        }

        
    }

}
