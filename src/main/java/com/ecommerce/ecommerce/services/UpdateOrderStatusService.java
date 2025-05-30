package com.ecommerce.ecommerce.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ecommerce.ecommerce.entities.Order;
import com.ecommerce.ecommerce.entities.OrderStatus;
import com.ecommerce.ecommerce.repo.OrderRepo;

@Service
public class UpdateOrderStatusService {
    
    @Autowired
    private OrderRepo orderRepo;

    public String update(String orderId, String statusStr) {
        Optional<Order> optionalOrder = orderRepo.findById(orderId);

        if (!optionalOrder.isPresent()) {
            throw new IllegalArgumentException("Order not found with id: " + orderId);
        }

        Order order = optionalOrder.get();

        OrderStatus status;
        try {
            status = OrderStatus.valueOf(statusStr.toUpperCase());
        } catch (IllegalArgumentException e) {
            throw new IllegalArgumentException("Invalid status value: " + statusStr);
        }

        order.setStatus(status);
        orderRepo.save(order);

        return "Order status updated successfully to " + status;
    }
}
