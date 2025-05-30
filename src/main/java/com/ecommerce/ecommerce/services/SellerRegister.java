package com.ecommerce.ecommerce.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ecommerce.ecommerce.entities.Seller;
import com.ecommerce.ecommerce.repo.SellerRepo;

@Service
public class SellerRegister {
	@Autowired
	private SellerRepo sellerRepo;
	
	public String register(Seller seller) {
	    if (sellerRepo.findByEmail(seller.getEmail()) != null) {
	        return "Email already registered";
	    }
	    if (sellerRepo.findByName(seller.getName()) != null) {
	        return "Username already taken";
	    }

	    sellerRepo.save(seller);
	    return "Registered successfully";
	}


}
