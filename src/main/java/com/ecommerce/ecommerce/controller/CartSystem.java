package com.ecommerce.ecommerce.controller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.ecommerce.ecommerce.entities.UsersCarts;
import com.ecommerce.ecommerce.services.AddItemToUsersCarts;
import com.ecommerce.ecommerce.services.DeleteItemFromUsersCarts;
import com.ecommerce.ecommerce.services.GetUsersCartsList;

@RestController
@RequestMapping("/cart")
public class CartSystem {

    @Autowired
    private GetUsersCartsList getUsersCartsList;

    @Autowired
    private AddItemToUsersCarts addItemToUsersCarts;

    @Autowired
    private DeleteItemFromUsersCarts deleteItemFromUsersCarts;

    @PostMapping("/userscarts")
    public ResponseEntity<?> getUserCart(@RequestBody Map<String, String> body) {
        String name = body.get("name");
        if (name == null || name.isEmpty()) {
            return ResponseEntity.badRequest().body("Missing or invalid name.");
        }

        List<String> cartItems = getUsersCartsList.getUserCartByName(name);

        if (cartItems.isEmpty()) {
            return ResponseEntity.ok("Your cart is empty.");
        }

        return ResponseEntity.ok(cartItems);
    }


    @PostMapping("/add")
    public ResponseEntity<String> addToCart(@RequestBody UsersCarts usersCarts) {
        if (usersCarts.getName() != null && usersCarts.getItemId() != null) {
            String response = addItemToUsersCarts.addItem(usersCarts);
            return ResponseEntity.ok(response);
        }
        return ResponseEntity.badRequest().body("Invalid item data. Please check all required fields.");
    }

    @DeleteMapping("/delete")
    public ResponseEntity<String> deleteCartItem(@RequestBody UsersCarts usersCarts) {
        if (usersCarts.getName() != null && usersCarts.getItemId() != null) {
            String response = deleteItemFromUsersCarts.deleteItem(usersCarts);
            return ResponseEntity.ok(response);
        }
        return ResponseEntity.badRequest().body("Please send a valid name and item ID.");
    }
}
