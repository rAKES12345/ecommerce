package com.ecommerce.ecommerce.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ecommerce.ecommerce.entities.Item;
import com.ecommerce.ecommerce.repo.ItemRepo;

@Service
public class DeleteItem {

    @Autowired
    private ItemRepo itemRepo;

    public String deleteItemByIdAndSellerId(String itemId, String sellerId) {
        if (itemId == null || itemId.trim().isEmpty()) {
            return "Invalid item ID";
        }
        if (sellerId == null || sellerId.trim().isEmpty()) {
            return "Seller ID is required";
        }

        Optional<Item> optionalItem = itemRepo.findById(itemId);
        if (optionalItem.isPresent()) {
            Item item = optionalItem.get();
            if (sellerId.equals(item.getSellerId())) {
                itemRepo.deleteById(itemId);
                return "Item deleted successfully";
            } else {
                return "You are not authorized to delete this item";
            }
        } else {
            return "Item not found";
        }
    }
}
