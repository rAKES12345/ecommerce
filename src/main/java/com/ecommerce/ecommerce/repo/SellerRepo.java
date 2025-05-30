package com.ecommerce.ecommerce.repo;

import org.springframework.data.mongodb.repository.MongoRepository;


import com.ecommerce.ecommerce.entities.Seller;

public interface SellerRepo extends MongoRepository<Seller, String> {
    Seller findByName(String name);
    Seller findByEmail(String email);
    
}
