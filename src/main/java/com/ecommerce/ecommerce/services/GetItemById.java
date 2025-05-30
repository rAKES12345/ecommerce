package com.ecommerce.ecommerce.services;

import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ecommerce.ecommerce.entities.Item;
import com.ecommerce.ecommerce.repo.ItemRepo;

@Service
public class GetItemById {

    @Autowired
    private ItemRepo itemRepo;

    public Item getItemById(String id) {
        if (id == null || id.trim().isEmpty()) {
            return null;
        }

        try {
            ObjectId objectId = new ObjectId(id.trim());
            return itemRepo.findById(objectId).orElse(null);
        } catch (IllegalArgumentException e) {
            return null; // Invalid ObjectId format
        }
    }
}
