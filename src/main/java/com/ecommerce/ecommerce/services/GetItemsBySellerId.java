package com.ecommerce.ecommerce.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ecommerce.ecommerce.entities.Item;
import com.ecommerce.ecommerce.repo.ItemRepo;


@Service
public class GetItemsBySellerId {

    @Autowired
    private ItemRepo itemRepo;

    public List<Item> getItems(String sellerId) {
        return itemRepo.findItemsBySellerId(sellerId);
    }
}
