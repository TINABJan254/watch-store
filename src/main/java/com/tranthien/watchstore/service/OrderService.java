package com.tranthien.watchstore.service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.tranthien.watchstore.domain.Order;
import com.tranthien.watchstore.repository.OrderRepository;

@Service
public class OrderService {
    
    private final OrderRepository orderRepository;

    public OrderService(OrderRepository orderRepository){
        this.orderRepository = orderRepository;
    }

    public List<Order> fetchOrder(){
        return this.orderRepository.findAll();
    }

    public Order handleSaveOrder(Order order){
        return this.orderRepository.save(order);
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
        this.orderRepository.deleteById(id);
    }

}
