package com.ecommerce.ecommerce.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ecommerce.ecommerce.entities.Admin;
import com.ecommerce.ecommerce.repo.AdminRepo;

@Service
public class AdminGetProfile {

    @Autowired
    private AdminRepo adminRepo;

  
    public String get(String name) {
        if (name == null || name.trim().isEmpty()) {
            return null;
        }
        Admin admin = adminRepo.findByName(name.trim()).orElse(null);
        if (admin == null) {
            return null;
        }
        return admin.getImage();
    }
}
