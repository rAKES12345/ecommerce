package com.ecommerce.ecommerce.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ecommerce.ecommerce.entities.Seller;
import com.ecommerce.ecommerce.repo.SellerRepo;

@Service
public class GetSellerDetailsById {

    @Autowired
    private SellerRepo sellerRepo;

    public Seller getSellerDetails(String id) {
        Optional<Seller> optionalSeller = sellerRepo.findById(id);
        return optionalSeller.orElse(null); // or throw an exception if preferred
    }
}
