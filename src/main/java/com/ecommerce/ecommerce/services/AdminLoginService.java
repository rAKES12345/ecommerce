package com.ecommerce.ecommerce.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ecommerce.ecommerce.entities.Admin;
import com.ecommerce.ecommerce.repo.AdminRepo;


@Service
public class AdminLoginService {

    @Autowired
    private AdminRepo adminRepository;

    public String login(String name, String password) {
        Optional<Admin> adminOpt = adminRepository.findByName(name);

        if (adminOpt.isPresent()) {
            Admin admin = adminOpt.get();
            // For production, use encrypted passwords with hashing (e.g., BCrypt)
            if (admin.getPassword().equals(password)) {
                return "Login successful";
            } else {
                return "Invalid password";
            }
        } else {
            return "Admin not found";
        }
    }
}
