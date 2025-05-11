package com.ecommerce.ecommerce.repo;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.ecommerce.ecommerce.entities.User;

@Repository
public interface UserRepo extends MongoRepository<User, Long>{
	User findByName(String name);
	User findByEmail(String email);

}
