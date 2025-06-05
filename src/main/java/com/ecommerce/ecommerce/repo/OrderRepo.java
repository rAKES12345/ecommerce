package com.ecommerce.ecommerce.repo;

import java.util.List;
import java.util.Optional;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.ecommerce.ecommerce.entities.Order;

public interface OrderRepo extends MongoRepository<Order, String> {  // <Order, String> for MongoDB IDs

    List<Order> findByName(String name);

    List<Order> findBySellerId(String sellerId);

    // deleteById is inherited from MongoRepository and returns void, so remove this line

    boolean existsById(String id);

    long countBySellerId(String sellerId);

    // findById is inherited from MongoRepository, so you can remove this line

    // Optional: if you want, you can keep this but it's redundant
    Order findOrderById(String orderId);

}
