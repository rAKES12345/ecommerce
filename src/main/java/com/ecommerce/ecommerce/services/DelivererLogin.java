package com.ecommerce.ecommerce.services;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import com.ecommerce.ecommerce.entities.Deliverer;
import com.ecommerce.ecommerce.entities.Seller;
import com.ecommerce.ecommerce.repo.DelivererRepo;

@Service
public class DelivererLogin {

    @Autowired
    private DelivererRepo delivererRepo;

    public String login(Deliverer deliverer) {
        
        if (deliverer == null || 
            deliverer.getName() == null || 
            deliverer.getPassword() == null || 
            deliverer.getName().trim().isEmpty() || 
            deliverer.getPassword().trim().isEmpty()) {
            return "Name and password must not be empty";
        }

        Deliverer existingDeliverer = delivererRepo.findByName(deliverer.getName());

        if (existingDeliverer != null && 
            existingDeliverer.getPassword().equals(deliverer.getPassword())) {
            return "Welcome " + existingDeliverer.getName();
        } else {
            return "Invalid name or password";
        }
    }
    
  
}
