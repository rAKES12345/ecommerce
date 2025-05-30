package com.ecommerce.ecommerce.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ecommerce.ecommerce.entities.Order;
import com.ecommerce.ecommerce.repo.OrderRepo;

@Service
public class AddOrder {

	@Autowired
	private OrderRepo orderRepo;
	
	public String add(Order order) {
		if(order!=null) 
		 orderRepo.save(order);
		 return "Order added successfully !";
	}
}
