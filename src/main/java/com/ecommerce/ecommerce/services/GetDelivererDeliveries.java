package com.ecommerce.ecommerce.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ecommerce.ecommerce.entities.DelivererDeliveries;
import com.ecommerce.ecommerce.repo.DelivererDeliveriesRepo;

@Service
public class GetDelivererDeliveries {

    @Autowired
    private DelivererDeliveriesRepo delivererDeliveriesRepo;

    public List<DelivererDeliveries> get(String delivererId) {
        // Use the correct method that finds deliveries by delivererId
        List<DelivererDeliveries> res = delivererDeliveriesRepo.findByDelivererId(delivererId);
        return res;
    }
}
