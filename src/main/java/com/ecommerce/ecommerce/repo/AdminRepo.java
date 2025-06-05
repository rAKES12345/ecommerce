package com.ecommerce.ecommerce.repo;

import java.util.Optional;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.ecommerce.ecommerce.entities.Admin;

public interface AdminRepo extends MongoRepository<Admin, String> {

    // Find Admin by name (username)
    Admin findByName(String name);

    // Find Admin by email
    Optional<Admin> findByEmail(String email);

    // Check if an admin with given name exists
    boolean existsByName(String name);

    // Check if an admin with given email exists
    boolean existsByEmail(String email);

    // You can add more query methods if needed
}
