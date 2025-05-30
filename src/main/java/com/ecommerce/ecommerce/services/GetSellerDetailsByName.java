package com.ecommerce.ecommerce.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ecommerce.ecommerce.entities.Seller;
import com.ecommerce.ecommerce.repo.SellerRepo;

@Service
public class GetSellerDetailsByName {

	@Autowired
	private SellerRepo sellerRepo;
	
	public Seller getSellerDetails(String name){
		return sellerRepo.findByName(name);
		
	}
}
