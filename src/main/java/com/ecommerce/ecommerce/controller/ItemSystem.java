package com.ecommerce.ecommerce.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.ecommerce.ecommerce.entities.Item;
import com.ecommerce.ecommerce.services.AddItem;
import com.ecommerce.ecommerce.services.DeleteItem;
import com.ecommerce.ecommerce.services.GetAllItems;
import com.ecommerce.ecommerce.services.GetItemById;
import com.ecommerce.ecommerce.services.GetItemsBySellerId;
import com.ecommerce.ecommerce.services.GetSellerIdByName;
import com.ecommerce.ecommerce.services.TotalSellersProducts;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

@RestController
@RequestMapping("/item")
@CrossOrigin
public class ItemSystem{
	 @Autowired
	    private AddItem addItemService;

	    @Autowired
	    private GetAllItems getAllItemsService;

	    @Autowired
	    private DeleteItem deleteItemService;

	    @Autowired
	    private GetItemById getItemByIdService;
	    
	    @Autowired
	    private GetSellerIdByName getSellerIdByName;
	    
	    @Autowired
	    private GetItemsBySellerId getItemsBySellerId;
	    
	    @Autowired
	    private TotalSellersProducts totalSellersProducts;

	    private final ObjectMapper objectMapper = new ObjectMapper();
	    
	    @PostMapping("/getselleridbyname")
	    public ResponseEntity<Map<String, String>> getSellerIdByName(@RequestBody Map<String, String> requestBody) {
	        String name = requestBody.get("name");  // Extract "name" from the JSON body

	        String sellerId = getSellerIdByName.getSellerId(name);

	        Map<String, String> response = new HashMap<>();
	        if (sellerId != null) {
	            response.put("sellerId", sellerId);
	            return ResponseEntity.ok(response);
	        } else {
	            response.put("error", "Seller not found");
	            return ResponseEntity.status(404).body(response);
	        }
	    }
	    

	    @PostMapping("/add")
	    public String addItem(@RequestBody Item item) {
	        return addItemService.addItem(item);
	    }
	    
	    @PostMapping("/getitemsbysellerid")
	    public ResponseEntity<?> getItemsBySellerId(@RequestBody Map<String, String> body) {
	        String sellerId = body.get("sellerId");
	        if (sellerId == null || sellerId.isEmpty()) {
	            return ResponseEntity.badRequest().body("sellerId is required");
	        }
	        List<Item> items = getItemsBySellerId.getItems(sellerId);
	        return ResponseEntity.ok(items);
	    }


	    @GetMapping("/all")
	    public ResponseEntity<?> getItems() {
	        List<Item> items = getAllItemsService.getAllItems();
	        if (items.isEmpty()) {
	            return ResponseEntity
	                    .status(HttpStatus.NOT_FOUND)
	                    .body("No items found");
	        } else {
	            return ResponseEntity.ok(items);
	        }
	    }

	    @PostMapping("/getitembyid")
	    public Item getItemById(@RequestBody String body) {
	        try {
	            JsonNode node = objectMapper.readTree(body);
	            String itemId = node.get("id").asText();
	            return getItemByIdService.getItemById(itemId);
	        } catch (Exception e) {
	            return null; // Or handle error properly
	        }
	    }

	    @DeleteMapping("/delete")
	    public String deleteItem(@RequestBody String body) {
	        try {
	            JsonNode node = objectMapper.readTree(body);
	            String itemId = node.get("id").asText();
	            String sellerId = node.get("sellerId").asText();
	            
	            return deleteItemService.deleteItemByIdAndSellerId(itemId,sellerId);
	        } catch (Exception e) {
	            return "Invalid request body";
	        }
	    }
	    
	    @PostMapping("/totalsellersproducts")
	    public ResponseEntity<?> totalsellersproducts(@RequestBody Map<String, String> request) {
	        String sellerId = request.get("sellerId");
	        if (sellerId == null || sellerId.trim().isEmpty()) {
	            return ResponseEntity.badRequest().body("Seller ID is required");
	        } else {
	            String res = totalSellersProducts.getTotal(sellerId);
	            return ResponseEntity.ok(res);
	        }
	    }
}
