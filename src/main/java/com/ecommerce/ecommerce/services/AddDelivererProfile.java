package com.ecommerce.ecommerce.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ecommerce.ecommerce.entities.Deliverer;
import com.ecommerce.ecommerce.entities.User;
import com.ecommerce.ecommerce.repo.DelivererRepo;

@Service
public class AddDelivererProfile {

	@Autowired
	private DelivererRepo delivererRepo;
	public String add(String image,String name) {
		Deliverer deliverer=delivererRepo.findByName(name);
		
		if(deliverer==null) {
			return "User not found";
		}
		deliverer.setImage(image);
		delivererRepo.save(deliverer);
		return "Profile image updated successfully";
	}
}
