package com.ecommerce.ecommerce.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ecommerce.ecommerce.entities.Seller;
import com.ecommerce.ecommerce.repo.SellerRepo;

@Service
public class GetSellerIdByName {

    @Autowired
    private SellerRepo sellerRepo;

    public String getSellerId(String name) {
        Seller seller = sellerRepo.findByName(name);
        return (seller != null) ? seller.getId() : null; // Assuming sellerId is a field in Seller
    }
}
