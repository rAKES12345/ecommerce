package com.ecommerce.ecommerce.repo;

import java.util.Optional;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.ecommerce.ecommerce.entities.DelivererDeliveries;

public interface DelivererDeliveriesRepo extends MongoRepository<DelivererDeliveries, String> {

    // This method is inherited from MongoRepository, but explicitly writing it is okay for clarity
    Optional<DelivererDeliveries> findById(String id);
    

    boolean existsByOrderIdAndDelivererId(String orderId, String delivererId);


    // Optional method to check if a delivery exists for a specific order and deliverer
    Optional<DelivererDeliveries> findByOrderIdAndDelivererId(String orderId, String delivererId);
}
