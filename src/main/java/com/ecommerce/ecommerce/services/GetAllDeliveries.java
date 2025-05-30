package com.ecommerce.ecommerce.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ecommerce.ecommerce.entities.DelivererDeliveries;
import com.ecommerce.ecommerce.repo.DelivererDeliveriesRepo;

@Service

public class GetAllDeliveries {

	@Autowired
	private DelivererDeliveriesRepo deliveryRepo;
	
	public List<DelivererDeliveries> get(){
		List res=deliveryRepo.findAll();
		return res;
	}
}
