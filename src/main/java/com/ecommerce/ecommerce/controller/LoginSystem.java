package com.ecommerce.ecommerce.controller;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ecommerce.ecommerce.entities.User;
import com.ecommerce.ecommerce.services.AddProfile;
import com.ecommerce.ecommerce.services.ForgotPassword;
import com.ecommerce.ecommerce.services.GetProfile;
import com.ecommerce.ecommerce.services.GetUserDetailsByName;
import com.ecommerce.ecommerce.services.Login;
import com.ecommerce.ecommerce.services.Register;

@RestController
@RequestMapping("/user")
public class LoginSystem {
	
	@Autowired
	private Register register;
	
	@Autowired
	private Login login;
	
	@Autowired
	private ForgotPassword forgotPassword;
	
	@Autowired
	private GetUserDetailsByName getUserDetailsByNameService;
	
	@Autowired
	private AddProfile addProfileService;
	
	@Autowired
	private GetProfile getProfile;
	
	@PostMapping("/login")
	public ResponseEntity<?> login(@RequestBody User user) {
	    if (user.getName() == null || user.getPassword() == null) {
	        return ResponseEntity
	                .badRequest()
	                .body(Map.of("error", "Please enter the username and password"));
	    }

	    String token = login.login(user);

	    if (token == null || token.isEmpty()) {
	        return ResponseEntity
	                .status(401)
	                .body(Map.of("error", "Invalid credentials"));
	    }

	    User loggedInUser = getUserDetailsByNameService.getUserDetails(user.getName());

	    Map<String, Object> response = Map.of(
	        "token", token,
	        "user", Map.of(
	            "username", loggedInUser.getName(),
	            "role", loggedInUser.getRole() 
	        )
	    );

	    return ResponseEntity.ok(response);
	}

	
	@PostMapping("/register")
	public String register(@RequestBody User user) {
		if(user.getName()==null || user.getPassword()==null || user.getEmail() ==null) {
			return "Please enter the email and name and password !";
		}else {		
			String res=register.register(user);
			return res;
		}
	}
	
	@PostMapping("/forgotpassword")
	public String forgotPassword(@RequestBody User user) {
		if(user.getName()==null || user.getPassword()==null || user.getEmail() ==null) {
			return "Please enter the email and name and password !";
		}else {		
			String res=forgotPassword.forgotPassword(user);
			return res;
		}
	}
	
	@PostMapping("/getuserdetailsbyname")
	public ResponseEntity<?> getUserDetailsByName(@RequestBody Map<String, String> body) {
	    String name = body.get("name");

	    if (name == null || name.trim().isEmpty()) {
	        return ResponseEntity.status(400).body("Username is required");
	    }

	    User user = getUserDetailsByNameService.getUserDetails(name.trim());

	    if (user == null) {
	        return ResponseEntity.status(404).body("No data found for the user");
	    }

	    return ResponseEntity.ok(user);
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

	    String profile = getProfile.get(name.trim());
	    if (profile == null) {
	        return ResponseEntity.status(404).body("Profile not found for user: " + name);
	    }
	    return ResponseEntity.ok(profile);
	}

	
	
}
