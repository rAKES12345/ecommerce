package com.ecommerce.ecommerce.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ecommerce.ecommerce.entities.Admin;
import com.ecommerce.ecommerce.repo.AdminRepo;

@Service
public class AdminForgotPassword {

    @Autowired
    private AdminRepo adminRepo;

    public String forgotPassword(Admin admin) {
        if (admin == null || admin.getName() == null || admin.getEmail() == null || admin.getPassword() == null) {
            return "Invalid input";
        }

        Optional<Admin> existingAdminOpt = adminRepo.findByName(admin.getName());

        if (existingAdminOpt.isEmpty()) {
            return "Admin user not found";
        }

        Admin existingAdmin = existingAdminOpt.get();

        // Verify email matches the name
        if (!existingAdmin.getEmail().equalsIgnoreCase(admin.getEmail())) {
            return "Email does not match";
        }

        // Update password
        existingAdmin.setPassword(admin.getPassword());
        adminRepo.save(existingAdmin);

        return "Password updated successfully";
    }
}
