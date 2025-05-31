package com.ecommerce.ecommerce.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ecommerce.ecommerce.entities.Order;
import com.ecommerce.ecommerce.repo.OrderRepo;

@Service
public class GetOrderById {

	@Autowired
	private OrderRepo orderRepo;
	
	public Order getOrderById(String id) {
		return orderRepo.findById(id).orElse(null);
	}
}
