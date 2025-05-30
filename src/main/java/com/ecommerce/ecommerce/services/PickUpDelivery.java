package com.ecommerce.ecommerce.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ecommerce.ecommerce.entities.DelivererDeliveries;
import com.ecommerce.ecommerce.entities.Order;
import com.ecommerce.ecommerce.entities.OrderStatus;
import com.ecommerce.ecommerce.repo.DelivererDeliveriesRepo;
import com.ecommerce.ecommerce.repo.OrderRepo;

@Service
public class PickUpDelivery {

    @Autowired
    private DelivererDeliveriesRepo delivererRepo;

    @Autowired
    private OrderRepo orderRepo;

    public String pickDelivery(DelivererDeliveries data) {
        // Check if this delivery already exists based on orderId and delivererId
        boolean alreadyExists = delivererRepo.existsByOrderIdAndDelivererId(
            data.getOrderId(), data.getDelivererId()  
        );

        if (alreadyExists) {
            return "Delivery already assigned for this order and deliverer";
        }

        // Fetch the order from the database
        Optional<Order> optionalOrder = orderRepo.findById(data.getOrderId());

        if (!optionalOrder.isPresent()) {
            return "Order not found";
        }

        Order order = optionalOrder.get();
        order.setStatus(OrderStatus.SHIPPED);
        orderRepo.save(order);       // Save the updated order

        // Save the delivery assignment
        DelivererDeliveries saved = delivererRepo.save(data);

        return "Delivery assigned successfully with ID: " + saved.getId();
    }
}
