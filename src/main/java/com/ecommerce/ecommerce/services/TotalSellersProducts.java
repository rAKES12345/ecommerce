package com.ecommerce.ecommerce.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ecommerce.ecommerce.repo.ItemRepo;
import com.ecommerce.ecommerce.repo.OrderRepo;

@Service
public class TotalSellersProducts {
	 @Autowired
	    private ItemRepo itemRepo;

	    public String getTotal(String sellerId) {
	        long total = itemRepo.countOfProductsBySellerId(sellerId);
	        return String.valueOf(total);
	    }
}
