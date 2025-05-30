package com.ecommerce.ecommerce.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ecommerce.ecommerce.entities.Deliverer;
import com.ecommerce.ecommerce.repo.DelivererRepo;

@Service
public class DelivererRegister {

    @Autowired
    private DelivererRepo delivererRepo;

    public String register(Deliverer deliverer) {
        if (deliverer == null || 
            deliverer.getName() == null || deliverer.getName().trim().isEmpty() || 
            deliverer.getEmail() == null || deliverer.getEmail().trim().isEmpty() || 
            deliverer.getPassword() == null || deliverer.getPassword().trim().isEmpty()) {
            return "Name, email, and password must not be empty";
        }

        // Check if email already exists
        if (delivererRepo.findByEmail(deliverer.getEmail()) != null) {
            return "Deliverer with this email already exists";
        }

        // Check if name already exists (optional)
        if (delivererRepo.findByName(deliverer.getName()) != null) {
            return "Deliverer with this name already exists";
        }

        delivererRepo.save(deliverer);
        return "Registration successful";
    }
}
