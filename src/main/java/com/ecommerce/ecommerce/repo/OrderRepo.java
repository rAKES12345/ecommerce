package com.ecommerce.ecommerce.repo;

import java.util.List;
import java.util.Optional;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.yaml.snakeyaml.events.Event.ID;

import com.ecommerce.ecommerce.entities.Order;

public interface OrderRepo extends MongoRepository<Order, Long>{
	List<Order>findByName(String name);
	List<Order>findBySellerId(String sellerId);
	String deleteById(String id);
	boolean existsById(String id);
	long countBySellerId(String sellerId);
	Optional<Order> findById(String orderId);

}
