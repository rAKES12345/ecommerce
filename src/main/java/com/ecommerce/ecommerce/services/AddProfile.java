package com.ecommerce.ecommerce.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ecommerce.ecommerce.entities.User;
import com.ecommerce.ecommerce.repo.UserRepo;

@Service

public class AddProfile {

	@Autowired
	private UserRepo userRepo;
	
	public String add(String name,String image) {
		User user=userRepo.findByName(name);
		
		if(user==null) {
			return "User not found";
		}
		user.setImage(image);
		userRepo.save(user);
		return "Profile image updated successfully";
		
	}
	
}
