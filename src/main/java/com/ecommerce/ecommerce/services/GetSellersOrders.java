package com.ecommerce.ecommerce.services;

import java.util.Collections;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ecommerce.ecommerce.entities.Order;
import com.ecommerce.ecommerce.repo.OrderRepo;

@Service 
public class GetSellersOrders {

	@Autowired 
	private OrderRepo orderRepo;
	
	public List<Order> getOrders(String sellerId){
		if (sellerId != null && !sellerId.trim().isEmpty()) {
            return orderRepo.findBySellerId(sellerId);
        }
        return Collections.emptyList();
	}
}
