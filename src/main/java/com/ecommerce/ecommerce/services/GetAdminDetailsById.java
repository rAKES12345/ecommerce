package com.ecommerce.ecommerce.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ecommerce.ecommerce.entities.Admin;
import com.ecommerce.ecommerce.repo.AdminRepo;

@Service
public class GetAdminDetailsById {

    @Autowired
    private AdminRepo adminRepo;

    
    public Admin getAdminDetails(String id) {
        if (id == null || id.trim().isEmpty()) {
            return null;
        }
        return adminRepo.findById(id.trim()).orElse(null);
    }
}
