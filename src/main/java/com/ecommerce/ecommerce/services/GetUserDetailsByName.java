package com.ecommerce.ecommerce.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ecommerce.ecommerce.entities.User;
import com.ecommerce.ecommerce.repo.UserRepo;

@Service
public class GetUserDetailsByName {

    @Autowired
    private UserRepo userRepo;

    public User getUserDetails(String name) {
        User user = userRepo.findByName(name);
        if (user != null) {
            user.setPassword(null);
        }
        return user;
    }
}
