package com.ecommerce.ecommerce.services;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Service;

import com.ecommerce.ecommerce.entities.User;
import com.ecommerce.ecommerce.repo.UserRepo;

@Service
public class Register {
	
	@Autowired
	private UserRepo userRepo;
	

	
	public String register(User user) {
		User emailExists=userRepo.findByEmail(user.getEmail());
		User nameExists=userRepo.findByName(user.getName());
		if(emailExists !=null || nameExists!=null) {
			  return "User with this email or name exists";
		}else {
			userRepo.save(user);
			System.out.println("Registration page");
			return "Registration success !";
			
		}
	}
}
