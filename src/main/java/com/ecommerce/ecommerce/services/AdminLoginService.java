package com.ecommerce.ecommerce.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ecommerce.ecommerce.components.JwtUtil;
import com.ecommerce.ecommerce.entities.Admin;
import com.ecommerce.ecommerce.repo.AdminRepo;

@Service
public class AdminLoginService {

    @Autowired
    private AdminRepo adminRepo;

    @Autowired
    private JwtUtil jwtUtil;

    // Validates admin credentials and returns the Admin if valid
    public Admin getAdminIfValid(String name, String password) {
        Optional<Admin> existingAdminOpt = adminRepo.findByName(name);

        if (existingAdminOpt.isPresent()) {
            Admin existingAdmin = existingAdminOpt.get();
            if (existingAdmin.getPassword().equals(password)) {
                return existingAdmin;
            }
        }

        return null;
    }

    // Generate JWT token for a valid admin
    public String generateToken(Admin admin) {
        return jwtUtil.generateTokenForAdmin(admin);
    }
}
