package com.ecommerce.ecommerce.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ecommerce.ecommerce.entities.Order;
import com.ecommerce.ecommerce.repo.OrderRepo;

@Service
public class GetAllOrders {

	@Autowired
	private OrderRepo orderRepo;
	
	public List<Order> get(){
		return orderRepo.findAll();
	}
}
