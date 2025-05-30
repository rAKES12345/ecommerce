package com.ecommerce.ecommerce.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ecommerce.ecommerce.repo.OrderRepo;

@Service
public class DeleteOrder {

    @Autowired
    private OrderRepo orderRepo;

    public String delete(String name, String orderId) {
        if (name == null || name.trim().isEmpty() || orderId == null || orderId.trim().isEmpty()) {
            return "Invalid request: 'name' and 'orderId' must not be null or empty.";
        }

        if (!orderRepo.existsById(orderId)) {
            return "Order with ID '" + orderId + "' not found.";
        }

        orderRepo.deleteById(orderId);
        return "Order with ID '" + orderId + "' deleted successfully.";
    }
}
