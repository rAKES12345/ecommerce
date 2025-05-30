package com.ecommerce.ecommerce.controller;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.ecommerce.ecommerce.entities.User;
import com.ecommerce.ecommerce.services.*;

@RestController
@RequestMapping("/user")
public class LoginSystem {

    @Autowired
    private Register registerService;

    @Autowired
    private Login loginService;

    @Autowired
    private ForgotPassword forgotPasswordService;

    @Autowired
    private GetUserDetailsByName getUserDetailsByNameService;

    @Autowired
    private AddProfile addProfileService;

    @Autowired
    private GetProfile getProfileService;

    // LOGIN
    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody User user) {
        if (user.getName() == null || user.getPassword() == null) {
            return ResponseEntity.badRequest().body(Map.of("error", "Please enter username and password"));
        }

        String token = loginService.login(user);
        if (token == null || token.isEmpty()) {
            return ResponseEntity.status(401).body(Map.of("error", "Invalid credentials"));
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

    // REGISTER
    @PostMapping("/register")
    public ResponseEntity<?> register(@RequestBody User user) {
        if (user.getName() == null || user.getPassword() == null || user.getEmail() == null) {
            return ResponseEntity.badRequest().body(Map.of("error", "Please enter name, email, and password"));
        }

        String result = registerService.register(user);

        if (result.equalsIgnoreCase("Registration success !")) {
            return ResponseEntity.ok(Map.of("message", result));
        } else {
            return ResponseEntity.status(400).body(Map.of("error", result));
        }
    }

    // FORGOT PASSWORD
    @PostMapping("/forgotpassword")
    public ResponseEntity<?> forgotPassword(@RequestBody User user) {
        if (user.getName() == null || user.getPassword() == null || user.getEmail() == null) {
            return ResponseEntity.badRequest().body(Map.of("error", "Please enter name, email, and new password"));
        }

        String result = forgotPasswordService.forgotPassword(user);
        return ResponseEntity.ok(Map.of("message", result));
    }

    // GET USER DETAILS BY NAME
    @PostMapping("/getuserdetailsbyname")
    public ResponseEntity<?> getUserDetailsByName(@RequestBody Map<String, String> body) {
        String name = body.get("name");

        if (name == null || name.trim().isEmpty()) {
            return ResponseEntity.badRequest().body(Map.of("error", "Username is required"));
        }

        User user = getUserDetailsByNameService.getUserDetails(name.trim());

        if (user == null) {
            return ResponseEntity.status(404).body(Map.of("error", "No data found for user"));
        }

        return ResponseEntity.ok(user);
    }

    // ADD PROFILE
    @PostMapping("/addprofile")
    public ResponseEntity<?> addProfile(@RequestBody Map<String, String> body) {
        String name = body.get("name");
        String image = body.get("image");

        if (name == null || name.trim().isEmpty()) {
            return ResponseEntity.badRequest().body(Map.of("error", "Name is required"));
        }
        if (image == null || image.trim().isEmpty()) {
            return ResponseEntity.badRequest().body(Map.of("error", "Image URL is required"));
        }

        String result = addProfileService.add(name.trim(), image.trim());
        return ResponseEntity.ok(Map.of("message", result));
    }

    // GET PROFILE
    @PostMapping("/getprofile")
    public ResponseEntity<?> getProfile(@RequestBody Map<String, String> body) {
        String name = body.get("name");

        if (name == null || name.trim().isEmpty()) {
            return ResponseEntity.badRequest().body(Map.of("error", "Name is required"));
        }

        String profile = getProfileService.get(name.trim());

        if (profile == null) {
            return ResponseEntity.status(404).body(Map.of("error", "Profile not found"));
        }

        return ResponseEntity.ok(Map.of("image", profile));
    }
}
