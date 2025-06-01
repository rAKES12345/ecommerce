package com.ecommerce.ecommerce.controller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ecommerce.ecommerce.entities.Order;
import com.ecommerce.ecommerce.services.AddOrder;
import com.ecommerce.ecommerce.services.DeleteOrder;
import com.ecommerce.ecommerce.services.GetAllOrders;
import com.ecommerce.ecommerce.services.GetOrderById;
import com.ecommerce.ecommerce.services.GetSellersOrders;
import com.ecommerce.ecommerce.services.GetUsersOrders;
import com.ecommerce.ecommerce.services.TotalSellersOreders;
import com.ecommerce.ecommerce.services.UpdateOrderStatusService;

import com.razorpay.RazorpayClient;
import org.json.JSONObject;

@RestController
@RequestMapping("/order")
public class OrderSystem {
	@Autowired
	private AddOrder addOrderService;
	
	@Autowired
	private GetUsersOrders getUsersOrdersService;
	
	@Autowired
	private DeleteOrder deleteOrderService;
	
	@Autowired
	private GetSellersOrders getSellersOrdersService;
	
	@Autowired
	private TotalSellersOreders totalSellersOrders;
	
	@Autowired
	private GetAllOrders getAllOrderService;
	
	@Autowired
	private GetOrderById getOrderByIdService;
	
	@Autowired
	private UpdateOrderStatusService updateOrderStatusService;

	@PostMapping("/create-payment-order")
	public ResponseEntity<?> createPaymentOrder(@RequestBody Map<String, Object> request) {
	    try {
	        RazorpayClient client = new RazorpayClient("rzp_test_M6EaAfst6tUkui", "2nqRrDSvGjaxaCqxLkPYKMWn");

	        Object amountObj = request.get("amount");
	        if (amountObj == null) {
	            return ResponseEntity.badRequest().body("Amount is required");
	        }

	        int amount = (int) amountObj;

	        JSONObject options = new JSONObject();
	        options.put("amount", amount * 100); // Amount in paise
	        options.put("currency", "INR");
	        options.put("receipt", "order_rcptid_11");
	        options.put("payment_capture", 1);

	        com.razorpay.Order razorpayOrder = client.orders.create(options);

	        return ResponseEntity.ok(razorpayOrder.toString());
	    } catch (Exception e) {
	        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
	                             .body("Error creating payment order: " + e.getMessage());
	    }
	}

	







	
	@PostMapping("/add")
	public ResponseEntity<?> add(@RequestBody Order order) {
	    if (order.getItemId() != null && 
	        order.getName() != null && 
	        order.getAddress()!=null &&
	        order.getSellerId()!=null &&
	        order.getPaymentMethod() != null) {

	        String response = addOrderService.add(order);
	        return ResponseEntity.ok(response);
	    } else {
	        return ResponseEntity
	                .badRequest()
	                .body("Missing required fields: id, itemId, name, or paymentMethod.");
	    }
	}

	
	@GetMapping("/getall")
	public ResponseEntity<?> get(){
		List<Order> orders=getAllOrderService.get();
		return ResponseEntity.ok(orders);
	}
	
	 @PostMapping("/getorderbyid")
	    public ResponseEntity<?> getOrderById(@RequestBody Map<String, String> request) {
	        String id = request.get("id");

	        if (id == null || id.isEmpty()) {
	            return ResponseEntity.badRequest().body("Order ID is required.");
	        }

	        try {
	            Order order = getOrderByIdService.getOrderById(id);
	            if (order != null) {
	                return ResponseEntity.ok(order);
	            } else {
	                return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Order not found with ID: " + id);
	            }
	        } catch (Exception e) {
	            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
	                                 .body("Error retrieving order: " + e.getMessage());
	        }
	    }
	
	@PostMapping("/getusersorders")
	public ResponseEntity<?> getUsersOrders(@RequestBody Map<String, String> request) {
	    String name = request.get("name");
	    if(name !=null) {
	    List<Order> orders = getUsersOrdersService.getOrders(name);
	   
	    if (orders == null || orders.isEmpty()) {
	        return ResponseEntity.status(404).body("No orders found for user: " + name);
	    }

	    return ResponseEntity.ok(orders);
	    }else {
	    	return ResponseEntity.status(401).body("name is required");
	    }
	}
	
	@PostMapping("/getbysellerorders")
	public ResponseEntity<?> getAllOrdersOfSeller(@RequestBody Map<String, String> request){
		String sellerId = request.get("sellerId");
		 if(sellerId !=null) {
			List<Order> orders=getSellersOrdersService.getOrders(sellerId);
			if (orders == null || orders.isEmpty()) {
		        return ResponseEntity.ok("No orders found for seller: " + sellerId);
		    }

		    return ResponseEntity.ok(orders);
		 }else {
		    	return ResponseEntity.status(401).body("seller id is required");
		    }
	}

	
	@DeleteMapping("/delete")
	public ResponseEntity<?> delete(@RequestBody Map<String, String> request) {
	    String name = request.get("name");
	    String orderId = request.get("orderId");

	    if (name == null || name.trim().isEmpty() || orderId == null || orderId.trim().isEmpty()) {
	        return ResponseEntity
	                .status(HttpStatus.BAD_REQUEST)
	                .body("Both 'name' and 'orderId' are required and cannot be empty.");
	    }

	    String result = deleteOrderService.delete(name, orderId);

	    if (result.contains("deleted successfully")) {
	        return ResponseEntity.ok(result);
	    } else if (result.contains("not found")) {
	        return ResponseEntity
	                .status(HttpStatus.NOT_FOUND)
	                .body(result);
	    } else {
	        return ResponseEntity
	                .status(HttpStatus.BAD_REQUEST)
	                .body(result);
	    }
	}
	
	@PostMapping("/totalsellersorders")
    public ResponseEntity<?> totalSellersOrders(@RequestBody Map<String, String> request) {
        String sellerId = request.get("sellerId");
        if (sellerId == null || sellerId.trim().isEmpty()) {
            return ResponseEntity.badRequest().body("Seller ID is required");
        } else {
            String res = totalSellersOrders.getTotal(sellerId);
            return ResponseEntity.ok(res);
        }
    }
	
	@PostMapping("/updateorderstatus")
	public ResponseEntity<?> updateOrderStatus(@RequestBody Map<String, String> request) {
	    String orderId = request.get("orderId");
	    String status = request.get("status");

	    if (orderId == null || status == null) {  // Use || instead of &&
	        return ResponseEntity.badRequest().body("OrderId and Status Required");
	    }

	    try {
	        String res = updateOrderStatusService.update(orderId, status);
	        return ResponseEntity.ok(res);
	    } catch (IllegalArgumentException e) {
	        return ResponseEntity.badRequest().body(e.getMessage());
	    } catch (Exception e) {
	        return ResponseEntity.status(500).body("Internal server error");
	    }
	}

	
	
}
