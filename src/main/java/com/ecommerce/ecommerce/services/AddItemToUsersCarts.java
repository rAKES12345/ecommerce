package com.ecommerce.ecommerce.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ecommerce.ecommerce.entities.UsersCarts;
import com.ecommerce.ecommerce.repo.UsersCartsRepo;

@Service
public class AddItemToUsersCarts {
	
	 @Autowired
	    private UsersCartsRepo usersCartRepo;

	    public String addItem(UsersCarts usersCarts) {
	        if (usersCarts.getName() == null || usersCarts.getItemId() == null) {
	            return "Invalid cart data";
	        }

	        // Prevent duplicate entries
	        UsersCarts existing = usersCartRepo.findByNameAndItemId(
	            usersCarts.getName(), usersCarts.getItemId()
	        );
	        
	        if (existing != null) {
	            return "Item already exists in user's cart";
	        }

	        usersCartRepo.save(usersCarts);
	        return "Item added to cart successfully";
		
	}
	
}
