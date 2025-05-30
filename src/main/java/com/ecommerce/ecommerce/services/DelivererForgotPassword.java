package com.ecommerce.ecommerce.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ecommerce.ecommerce.entities.Deliverer;
import com.ecommerce.ecommerce.repo.DelivererRepo;

@Service
public class DelivererForgotPassword {

    @Autowired
    private DelivererRepo delivererRepo;

    public String forgotPassword(Deliverer deliverer) {
        // Input validation
        if (deliverer == null || 
            deliverer.getEmail() == null || deliverer.getEmail().trim().isEmpty() ||
            deliverer.getPassword() == null || deliverer.getPassword().trim().isEmpty()) {
            
            return "Email and new password must not be empty";
        }

        // Find existing deliverer by email
        Deliverer existingDeliverer = delivererRepo.findByEmail(deliverer.getEmail());

        if (existingDeliverer == null) {
            return "No deliverer found with this email";
        }

        // Update password
        existingDeliverer.setPassword(deliverer.getPassword());
        delivererRepo.save(existingDeliverer);

        return "Password updated successfully";
    }
}
