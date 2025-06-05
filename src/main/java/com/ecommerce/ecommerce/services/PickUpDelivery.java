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
        System.out.println("Checking exists for orderId: " + data.getOrderId() + ", delivererId: " + data.getDelivererId());

        boolean alreadyExists = delivererRepo.existsByOrderIdAndDelivererId(
            data.getOrderId(), data.getDelivererId()
        );

        if (alreadyExists) {
            throw new IllegalStateException("Delivery already assigned for this order and deliverer");
        }

        Optional<Order> optionalOrder = orderRepo.findById(data.getOrderId());

        if (!optionalOrder.isPresent()) {
            throw new IllegalArgumentException("Order not found");
        }

        Order order = optionalOrder.get();

        order.setDelivereId(data.getDelivererId());  // Make sure this setter exists
        order.setStatus(OrderStatus.SHIPPED);

        orderRepo.save(order);

        DelivererDeliveries saved = delivererRepo.save(data);

        return saved.getId();
    }

}
