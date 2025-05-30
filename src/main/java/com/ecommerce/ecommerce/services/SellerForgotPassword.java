package com.ecommerce.ecommerce.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ecommerce.ecommerce.entities.Seller;
import com.ecommerce.ecommerce.repo.SellerRepo;

@Service
public class SellerForgotPassword {

    @Autowired
    private SellerRepo sellerRepo;

    // Updated method to accept name and new password separately
    public String forgotPassword(Seller seller) {
        Seller existingSeller = sellerRepo.findByEmail(seller.getEmail());

        if (existingSeller != null) {
            if (existingSeller.getName().trim().equalsIgnoreCase(seller.getName().trim())) {
            	existingSeller.setPassword(seller.getPassword());
                sellerRepo.save(existingSeller);  
                return "Password updated successfully";
            } else {
                return "Invalid name";
            }
        } else {
            return "User not found";
        }
    }
}
