package com.ecommerce.ecommerce.repo;

import java.util.List;
import java.util.Optional;

import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;

import com.ecommerce.ecommerce.entities.Item;

public interface ItemRepo extends MongoRepository<Item, ObjectId>{
	Optional<Item> findByName(String name);
	
	List<Item> findItemsBySellerId(String sellerId);

	
	String findBySellerId(String sellerId);
	Optional<Item> findById(String itemId);
	long countOfProductsBySellerId(String sellerId);
	
	
	String deleteById(String itemId);
}
