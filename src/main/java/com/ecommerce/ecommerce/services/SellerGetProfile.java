package com.ecommerce.ecommerce.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ecommerce.ecommerce.entities.Seller;

import com.ecommerce.ecommerce.repo.SellerRepo;

@Service
public class SellerGetProfile {

	@Autowired
	private SellerRepo sellerRepo;
	public String get(String name) {
    Seller seller = sellerRepo.findByName(name);
    if (seller == null) {
        return null;  
    }
    return seller.getImage();
}
}
