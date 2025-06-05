package com.ecommerce.ecommerce.controller;

import java.util.List;
import java.util.Map;

import org.apache.catalina.connector.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.ecommerce.ecommerce.entities.Deliverer;
import com.ecommerce.ecommerce.entities.DelivererDeliveries;
import com.ecommerce.ecommerce.entities.Seller;
import com.ecommerce.ecommerce.services.AddDelivererProfile;
import com.ecommerce.ecommerce.services.DelivererForgotPassword;
import com.ecommerce.ecommerce.services.DelivererLogin;
import com.ecommerce.ecommerce.services.DelivererRegister;
import com.ecommerce.ecommerce.services.GetAllDeliveries;
import com.ecommerce.ecommerce.services.GetDelivererDeliveries;
import com.ecommerce.ecommerce.services.GetDelivererDetailsByName;
import com.ecommerce.ecommerce.services.GetDelivererProfile;
import com.ecommerce.ecommerce.services.GetDeliveryDetails;
import com.ecommerce.ecommerce.services.PickUpDelivery;

@RestController
@RequestMapping("/deliverer")
public class DelivererSystem {

    @Autowired
    private DelivererLogin delivererLoginService;

    @Autowired
    private DelivererRegister delivererRegisterService;

    @Autowired
    private DelivererForgotPassword delivererForgotPasswordService;
    
    @Autowired
    private GetDelivererDetailsByName getDelivererDetailsByName;
    
    @Autowired
    private AddDelivererProfile addDelivererProfileService;
    
    @Autowired
    private GetDelivererProfile getProfileService;
    
    @Autowired
    private PickUpDelivery pickUpDeliveryService;
    
    @Autowired
    private GetDeliveryDetails getDeliveryDetailsService;

    @Autowired
    private GetAllDeliveries getAllDeliveries;
    
    @Autowired
    private GetDelivererDeliveries getDelivererDeliveriesService;
    
    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody Deliverer deliverer) {
        if (deliverer.getName() == null || deliverer.getPassword() == null) {
            return ResponseEntity.badRequest().body("Name and Password are required");
        }
        String res = delivererLoginService.login(deliverer);
        return ResponseEntity.ok(res);
    }

    @PostMapping("/register")
    public ResponseEntity<?> register(@RequestBody Deliverer deliverer) {
        if (deliverer.getName() == null || deliverer.getName().trim().isEmpty() ||
            deliverer.getPassword() == null || deliverer.getPassword().trim().isEmpty() ||
            deliverer.getEmail() == null || deliverer.getEmail().trim().isEmpty()) {
            return ResponseEntity.badRequest().body("Name, password, and email are required");
        }

        String result = delivererRegisterService.register(deliverer);

        if ("Registration successful".equals(result)) {
            return ResponseEntity.ok(result);
        } else {
            return ResponseEntity.status(409).body(result);
        }
    }

    @PostMapping("/forgotpassword")
    public ResponseEntity<?> forgotPassword(@RequestBody Deliverer deliverer) {
        if (deliverer.getName() == null || deliverer.getName().trim().isEmpty() ||
            deliverer.getPassword() == null || deliverer.getPassword().trim().isEmpty() ||
            deliverer.getEmail() == null || deliverer.getEmail().trim().isEmpty()) {
            return ResponseEntity.badRequest().body("Name, password, and email are required");
        }

        String result = delivererForgotPasswordService.forgotPassword(deliverer);

        if ("Password updated successfully ".equals(result)) {
            return ResponseEntity.ok(result);
        } else {
            return ResponseEntity.status(409).body(result);
        }
    }
    
   
    
    
    @PostMapping("/pickup")
    public ResponseEntity<String> pickUpDelivery(@RequestBody DelivererDeliveries data) {
        if (data.getOrderId() == null || data.getDelivererId() == null) {
            return ResponseEntity.badRequest().body("Order Id and Deliverer Id are required");
        }

        String response = pickUpDeliveryService.pickDelivery(data);

            return ResponseEntity.ok(response);
       
    }
    
    @GetMapping("/getalldeliveries")
    public ResponseEntity<?> getAllDeliveries(){
    	List<DelivererDeliveries> res= getAllDeliveries.get();
    	return ResponseEntity.ok(res);
    }
    
    @PostMapping("/getdelivererdeliveries")
    public ResponseEntity<List<DelivererDeliveries>> getDelivererDeliveries(@RequestBody Map<String, String> data) {
        String delivererId = data.get("delivererId");
        if (delivererId == null || delivererId.isEmpty()) {
            return ResponseEntity.badRequest().build();
        }

        List<DelivererDeliveries> res = getDelivererDeliveriesService.get(delivererId);
        return ResponseEntity.ok(res);
    }
    
    
    @PostMapping("/getdeliverydetails")
    public ResponseEntity<?> getDeliveryDetails(@RequestBody Map<String, String> data) {
        String deliveryId = data.get("deliveryId");

        if (deliveryId == null || deliveryId.trim().isEmpty()) {
            return ResponseEntity.badRequest().body(Map.of("error", "deliveryId is required"));
        }

        Map<String, Object> response = getDeliveryDetailsService.getDeliveryDetails(deliveryId);

        if (response.containsKey("error")) {
            return ResponseEntity.status(404).body(response);
        }

        return ResponseEntity.ok(response);
    }
    
    
    
    

    
    @PostMapping("/getdelivererdetailsbyname")
  	public ResponseEntity<?> getDelivererDetailsByName(@RequestBody Map<String, String> body) {
  	    String name = body.get("name");

  	    if (name == null || name.trim().isEmpty()) {
  	        return ResponseEntity.status(400).body("Name is required");
  	    }

  	    Deliverer deliverer = getDelivererDetailsByName.getDelivereDetails(name.trim());

  	    if (deliverer == null) {
  	        return ResponseEntity.status(404).body("No data found for the deliverer");
  	    }

  	    return ResponseEntity.ok(deliverer);
  	}
      
      @PostMapping("/addprofile")
  		public ResponseEntity<Map <String,String >> addProfile(@RequestBody Map<String,String> requestBody){
  		String name=requestBody.get("name");
  		String image=requestBody.get("image");
  		 if (name == null || name.trim().isEmpty()) {
  		        return ResponseEntity.badRequest().body(Map.of("error", "Name is required"));
  		    }
  		    if (image == null || image.trim().isEmpty()) {
  		        return ResponseEntity.badRequest().body(Map.of("error", "Image URL is required"));
  		    }

  		String result=addDelivererProfileService.add(name.trim(), image.trim());
  		 Map<String, String> response = Map.of("message", result);
  		    return ResponseEntity.ok(response);
  	}
  	
  	@PostMapping("/getprofile")
  	public ResponseEntity<?> getProfile(@RequestBody Map<String, String> requestBody) {
  	    String name = requestBody.get("name");
  	    if (name == null || name.trim().isEmpty()) {
  	        return ResponseEntity.badRequest().body("Name is required");
  	    }

  	    String profile = getProfileService.get(name.trim());
  	    if (profile == null) {
  	        return ResponseEntity.status(404).body("Profile not found for deliverer: " + name);
  	    }
  	    return ResponseEntity.ok(profile);
  	}
}
