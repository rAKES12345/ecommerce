package com.ecommerce.ecommerce.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ecommerce.ecommerce.entities.Deliverer;
import com.ecommerce.ecommerce.repo.DelivererRepo;


@Service
public class GetDelivererDetailsByName {
	@Autowired
	private DelivererRepo delivererRepo;
	
	public Deliverer getDelivereDetails(String name){
		return delivererRepo.findByName(name);
		
	}
}
