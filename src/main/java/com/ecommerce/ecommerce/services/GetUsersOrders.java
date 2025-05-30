package com.ecommerce.ecommerce.services;

import java.util.Collections;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ecommerce.ecommerce.entities.Order;
import com.ecommerce.ecommerce.repo.OrderRepo;

@Service
public class GetUsersOrders {

    @Autowired
    private OrderRepo orderRepo;

    public List<Order> getOrders(String name) {
        if (name != null && !name.trim().isEmpty()) {
            return orderRepo.findByName(name);
        }
        return Collections.emptyList();
    }
}
