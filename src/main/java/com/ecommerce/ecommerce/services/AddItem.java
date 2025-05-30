package com.ecommerce.ecommerce.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ecommerce.ecommerce.entities.Item;
import com.ecommerce.ecommerce.entities.Seller;
import com.ecommerce.ecommerce.repo.ItemRepo;
import com.ecommerce.ecommerce.repo.SellerRepo;

@Service
public class AddItem {

    @Autowired
    private ItemRepo itemRepo;

    @Autowired
    private SellerRepo sellerRepo;  

    public String addItem(Item item) {
        // Check if seller exists
        Optional<Seller> seller = sellerRepo.findById(item.getSellerId());
        if (!seller.isPresent()) {
            return "Invalid seller ID. Cannot add item.";
        }

        // Check if item with the same name exists
        Optional<Item> existingItem = itemRepo.findByName(item.getName());
        if (existingItem.isPresent()) {
            return "Item with the same name already exists";
        }

        itemRepo.save(item);
        return "Item saved successfully";
    }
}
