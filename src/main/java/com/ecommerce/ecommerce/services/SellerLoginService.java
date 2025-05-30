package com.ecommerce.ecommerce.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ecommerce.ecommerce.entities.Seller;
import com.ecommerce.ecommerce.repo.SellerRepo;

@Service
public class SellerLoginService {
	
	@Autowired
	private SellerRepo sellerRepo;
	
	public String login(Seller seller) {
		Seller existingSeller=sellerRepo.findByName(seller.getName());
		if(existingSeller!=null && existingSeller.getPassword().equals(seller.getPassword())) {
			return "Login successful";
		}else {
			return "Please check username and password";
		}
		
	}
}
