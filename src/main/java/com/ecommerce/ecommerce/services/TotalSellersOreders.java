package com.ecommerce.ecommerce.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ecommerce.ecommerce.repo.OrderRepo;

@Service
public class TotalSellersOreders {
	
	 @Autowired
	    private OrderRepo orderRepo;

	    public String getTotal(String sellerId) {
	        long total = orderRepo.countBySellerId(sellerId);
	        return String.valueOf(total);
	    }
}
