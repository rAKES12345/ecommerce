package com.ecommerce.ecommerce.services;

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

    public Admin getAdminIfValid(String name, String password) {
        Admin existingAdmin = adminRepo.findByNameForLogin(name);
        if (existingAdmin != null && existingAdmin.getPassword().equals(password)) {
            return existingAdmin;
        }
        return null;
    }

    public String generateToken(Admin admin) {
        return jwtUtil.generateTokenForAdmin(admin);
    }
}
