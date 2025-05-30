package com.ecommerce.ecommerce.repo;

import org.springframework.data.mongodb.repository.MongoRepository;
import com.ecommerce.ecommerce.entities.Deliverer;

public interface DelivererRepo extends MongoRepository<Deliverer, String> {
    Deliverer findByName(String name);
    Deliverer findByEmail(String email);
}
