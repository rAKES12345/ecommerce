package com.ecommerce.ecommerce.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ecommerce.ecommerce.entities.UsersCarts;
import com.ecommerce.ecommerce.repo.UsersCartsRepo;

@Service
public class DeleteItemFromUsersCarts {

    @Autowired
    private UsersCartsRepo usersCartsRepo;

    public String deleteItem(UsersCarts usersCarts) {
        if (usersCarts == null || usersCarts.getName() == null || usersCarts.getItemId() == null) {
            return "Invalid input data. Name and Item ID must not be null.";
        }

        int deletedCount = usersCartsRepo.deleteByNameAndItemId(usersCarts.getName(), usersCarts.getItemId());
        if (deletedCount > 0) {
            return "Item deleted successfully.";
        } else {
            return "Item not found for the given user.";
        }
    }
}
