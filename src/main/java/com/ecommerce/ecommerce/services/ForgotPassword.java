package com.ecommerce.ecommerce.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ecommerce.ecommerce.entities.User;
import com.ecommerce.ecommerce.repo.UserRepo;

@Service
public class ForgotPassword {

    @Autowired
    private UserRepo userRepo;

    public String forgotPassword(User user) {
        User existingUser = userRepo.findByEmail(user.getEmail());

        if (existingUser != null) {
            if (existingUser.getName().trim().equalsIgnoreCase(user.getName().trim())) {
                existingUser.setPassword(user.getPassword());
                userRepo.save(existingUser);  
                return "Password updated successfully";
            } else {
                return "Invalid name";
            }
        } else {
            return "User not found";
        }
    }
}
