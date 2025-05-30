package com.ecommerce.ecommerce.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ecommerce.ecommerce.entities.User;
import com.ecommerce.ecommerce.repo.UserRepo;

@Service
public class GetProfile {

    @Autowired
    private UserRepo userRepo;

    public String get(String name) {
        User user = userRepo.findByName(name);
        if (user == null) {
            return null;  
        }
        return user.getImage();
    }
}
