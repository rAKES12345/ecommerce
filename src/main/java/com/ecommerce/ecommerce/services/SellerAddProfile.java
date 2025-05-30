package com.ecommerce.ecommerce.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ecommerce.ecommerce.entities.Seller;

import com.ecommerce.ecommerce.repo.SellerRepo;


@Service
public class SellerAddProfile {

	@Autowired
	private SellerRepo sellerRepo;
	
	public String add(String name,String image) {
		Seller seller=sellerRepo.findByName(name);
		
		if(seller==null) {
			return "User not found";
		}
		seller.setImage(image);
		sellerRepo.save(seller);
		return "Profile image updated successfully";
		
	}
}
