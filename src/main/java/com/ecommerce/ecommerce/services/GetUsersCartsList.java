package com.ecommerce.ecommerce.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ecommerce.ecommerce.entities.UsersCarts;
import com.ecommerce.ecommerce.repo.UsersCartsRepo;

@Service
public class GetUsersCartsList {

    @Autowired
    private UsersCartsRepo usersCartRepo;

    public List<String> getUserCartByName(String name) {
    	List<UsersCarts> carts=usersCartRepo.findByName(name);
    	if(carts==null || carts.isEmpty()) {
    		return List.of();
    	}
    	return carts.stream()
    			.map(UsersCarts::getItemId)
    			.toList();
    }

}
