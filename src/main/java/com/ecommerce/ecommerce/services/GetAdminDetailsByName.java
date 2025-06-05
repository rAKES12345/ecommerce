package com.ecommerce.ecommerce.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ecommerce.ecommerce.entities.Admin;
import com.ecommerce.ecommerce.repo.AdminRepo;

@Service
public class GetAdminDetailsByName {

    @Autowired
    private AdminRepo adminRepo;

   
    public Admin getAdminDetails(String name) {
        if (name == null || name.trim().isEmpty()) {
            return null;
        }
        
        Optional<Admin> adminOpt = adminRepo.findByName(name.trim());
        return adminOpt.orElse(null);
    }
}
