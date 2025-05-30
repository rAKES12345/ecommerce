package com.ecommerce.ecommerce.controller;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.ecommerce.ecommerce.entities.Seller;
import com.ecommerce.ecommerce.entities.User;
import com.ecommerce.ecommerce.services.GetSellerDetailsById;
import com.ecommerce.ecommerce.services.GetSellerDetailsByName;
import com.ecommerce.ecommerce.services.SellerAddProfile;
import com.ecommerce.ecommerce.services.SellerForgotPassword;
import com.ecommerce.ecommerce.services.SellerGetProfile;
import com.ecommerce.ecommerce.services.SellerLoginService;
import com.ecommerce.ecommerce.services.SellerRegister;

@RestController
@RequestMapping("/seller")
public class LoginSystemForSeller {

    @Autowired
    private SellerLoginService sellerLoginService;

    @Autowired
    private SellerRegister sellerRegister;

    @Autowired
    private SellerForgotPassword sellerForgotPassword;
    
    @Autowired
    private GetSellerDetailsByName getSellerDetailsByNameService;
    
    @Autowired
    private SellerAddProfile addProfileService;
    
    @Autowired
    private SellerGetProfile getProfileService;
    
    @Autowired
    private GetSellerDetailsById getSellerDetailsByIdService;

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody Seller seller) {
        if (seller.getName() != null && seller.getPassword() != null) {
            String result = sellerLoginService.login(seller);

            if ("Login successful".equalsIgnoreCase(result)) {
                return ResponseEntity.ok(Map.of("message", result));
            } else {
                return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(Map.of("message", result));
            }
        } else {
            return ResponseEntity
                    .badRequest()
                    .body(Map.of("message", "Please enter the username and password"));
        }
    }

    @PostMapping("/register")
    public ResponseEntity<?> register(@RequestBody Seller seller) {
        if (seller.getName() == null || seller.getPassword() == null || seller.getEmail() == null) {
            return ResponseEntity
                    .badRequest()
                    .body(Map.of("message", "Please enter username, email, and password"));
        }

        String res = sellerRegister.register(seller);

        switch (res.toLowerCase()) {
            case "registered successfully":
                return ResponseEntity.ok(Map.of("message", "Registered successfully"));

            case "email already registered":
                return ResponseEntity
                        .status(HttpStatus.CONFLICT)
                        .body(Map.of("message", "Email already registered"));

            case "username already taken":
                return ResponseEntity
                        .status(HttpStatus.CONFLICT)
                        .body(Map.of("message", "Username already taken"));

            default:
                return ResponseEntity
                        .status(HttpStatus.INTERNAL_SERVER_ERROR)
                        .body(Map.of("message", "Something went wrong"));
        }
    }

    @PostMapping("/forgotpassword")
    public ResponseEntity<?> forgotPassword(@RequestBody Seller seller) {
        if (seller.getName() != null && seller.getPassword() != null && seller.getEmail() != null) {
            String result = sellerForgotPassword.forgotPassword(seller);

            if ("Password updated successfully".equalsIgnoreCase(result)) {
                return ResponseEntity.ok(Map.of("message", result));
            } else if ("User not found".equalsIgnoreCase(result)) {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body(Map.of("message", result));
            } else {
                return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(Map.of("message", result));
            }
        } else {
            return ResponseEntity
                    .badRequest()
                    .body(Map.of("message", "Please enter the username, email, and new password"));
        }
    }
    
    @PostMapping("/getsellerdetailsbyname")
	public ResponseEntity<?> getSellerDetailsByName(@RequestBody Map<String, String> body) {
	    String name = body.get("name");

	    if (name == null || name.trim().isEmpty()) {
	        return ResponseEntity.status(400).body("Name is required");
	    }

	    Seller seller = getSellerDetailsByNameService.getSellerDetails(name.trim());

	    if (seller == null) {
	        return ResponseEntity.status(404).body("No data found for the seller");
	    }

	    return ResponseEntity.ok(seller);
	}
    
    
    @PostMapping("/getsellerdetailsbyid")
   	public ResponseEntity<?> getSellerDetailsById(@RequestBody Map<String, String> body) {
   	    String id = body.get("id");

   	    if (id == null || id.trim().isEmpty()) {
   	        return ResponseEntity.status(400).body("Id is required");
   	    }

   	    Seller seller = getSellerDetailsByIdService.getSellerDetails(id.trim());

   	    if (seller == null) {
   	        return ResponseEntity.status(404).body("No data found for the seller");
   	    }

   	    return ResponseEntity.ok(seller);
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

		String result=addProfileService.add(name.trim(), image.trim());
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
	        return ResponseEntity.status(404).body("Profile not found for user: " + name);
	    }
	    return ResponseEntity.ok(profile);
	}
    
}
