package com.ecommerce.ecommerce.repo;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.ecommerce.ecommerce.entities.UsersCarts;

@Repository
public interface UsersCartsRepo extends MongoRepository<UsersCarts, Long>{
	UsersCarts findByNameAndItemId(String name, String itemId);
	UsersCarts findByItemId(String id);
	
	 List<UsersCarts> findByName(String name); 
	int deleteByNameAndItemId(String name, String itemId);
}
