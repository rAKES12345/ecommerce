package com.ecommerce.ecommerce.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ecommerce.ecommerce.entities.Admin;
import com.ecommerce.ecommerce.repo.AdminRepo;

@Service
public class AdminAddProfile {

    @Autowired
    private AdminRepo adminRepo;

   
    public String add(String name, String image) {
        if (name == null || name.trim().isEmpty()) {
            return "Admin name is required";
        }
        if (image == null || image.trim().isEmpty()) {
            return "Image URL is required";
        }

        Admin admin = adminRepo.findByName(name.trim()).orElse(null);

        if (admin == null) {
            return "Admin not found";
        }

        admin.setImage(image.trim());
        adminRepo.save(admin);

        return "Profile image updated successfully";
    }
}
