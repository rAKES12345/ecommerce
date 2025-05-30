package com.ecommerce.ecommerce.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ecommerce.ecommerce.components.JwtUtil;
import com.ecommerce.ecommerce.entities.User;
import com.ecommerce.ecommerce.repo.UserRepo;

@Service
public class Login {

    @Autowired
    private UserRepo userRepo;

    @Autowired
    private JwtUtil jwtUtil;

    public String login(User user) {
        User existingUser = userRepo.findByName(user.getName());

        if (existingUser != null && existingUser.getPassword().equals(user.getPassword())) {
            return jwtUtil.generateToken(existingUser);
        }

        return null;
    }
}
