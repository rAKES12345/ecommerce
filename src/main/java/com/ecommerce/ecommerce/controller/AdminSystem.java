package com.ecommerce.ecommerce.controller;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.ecommerce.ecommerce.entities.Admin;
import com.ecommerce.ecommerce.services.AdminAddProfile;
import com.ecommerce.ecommerce.services.AdminForgotPassword;
import com.ecommerce.ecommerce.services.AdminGetProfile;
import com.ecommerce.ecommerce.services.AdminLoginService;

import com.ecommerce.ecommerce.services.GetAdminDetailsById;
import com.ecommerce.ecommerce.services.GetAdminDetailsByName;

@RestController
@RequestMapping("/admin")
public class AdminSystem {

    @Autowired
    private AdminLoginService adminLoginService;

 

    @Autowired
    private AdminForgotPassword adminForgotPassword;

    @Autowired
    private GetAdminDetailsByName getAdminDetailsByNameService;

    @Autowired
    private AdminAddProfile adminAddProfileService;

    @Autowired
    private AdminGetProfile adminGetProfileService;

    @Autowired
    private GetAdminDetailsById getAdminDetailsByIdService;

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody Admin admin) {
        if (admin.getName() == null || admin.getPassword() == null) {
            return ResponseEntity.badRequest().body(Map.of("error", "Please enter username and password"));
        }

        Admin existingAdmin = adminLoginService.getAdminIfValid(admin.getName(), admin.getPassword());
        if (existingAdmin == null) {
            return ResponseEntity.status(401).body(Map.of("error", "Invalid credentials"));
        }

        String token = adminLoginService.generateToken(existingAdmin);

        Map<String, Object> response = Map.of(
            "token", token,
            "admin", Map.of(
                "name", existingAdmin.getName(),
                "role", existingAdmin.getRole()
            )
        );

        return ResponseEntity.ok(response);
    }



   
    @PostMapping("/forgotpassword")
    public ResponseEntity<?> forgotPassword(@RequestBody Admin admin) {
        if (admin.getName() != null && admin.getPassword() != null && admin.getEmail() != null) {
            String result = adminForgotPassword.forgotPassword(admin);

            if ("Password updated successfully".equalsIgnoreCase(result)) {
                return ResponseEntity.ok(Map.of("message", result));
            } else if ("User not found".equalsIgnoreCase(result)) {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body(Map.of("message", result));
            } else {
                return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(Map.of("message", result));
            }
        } else {
            return ResponseEntity.badRequest().body(Map.of("message", "Please enter the username, email, and new password"));
        }
    }

    @PostMapping("/getadmindetailsbyname")
    public ResponseEntity<?> getAdminDetailsByName(@RequestBody Map<String, String> body) {
        String name = body.get("name");

        if (name == null || name.trim().isEmpty()) {
            return ResponseEntity.status(400).body("Name is required");
        }

        Admin admin = getAdminDetailsByNameService.getAdminDetails(name.trim());

        if (admin == null) {
            return ResponseEntity.status(404).body("No data found for the admin");
        }

        return ResponseEntity.ok(admin);
    }

    @PostMapping("/getadmindetailsbyid")
    public ResponseEntity<?> getAdminDetailsById(@RequestBody Map<String, String> body) {
        String id = body.get("id");

        if (id == null || id.trim().isEmpty()) {
            return ResponseEntity.status(400).body("Id is required");
        }

        Admin admin = getAdminDetailsByIdService.getAdminDetails(id.trim());

        if (admin == null) {
            return ResponseEntity.status(404).body("No data found for the admin");
        }

        return ResponseEntity.ok(admin);
    }

    @PostMapping("/addprofile")
    public ResponseEntity<Map<String, String>> addProfile(@RequestBody Map<String, String> requestBody) {
        String name = requestBody.get("name");
        String image = requestBody.get("image");

        if (name == null || name.trim().isEmpty()) {
            return ResponseEntity.badRequest().body(Map.of("error", "Name is required"));
        }
        if (image == null || image.trim().isEmpty()) {
            return ResponseEntity.badRequest().body(Map.of("error", "Image URL is required"));
        }

        String result = adminAddProfileService.add(name.trim(), image.trim());
        return ResponseEntity.ok(Map.of("message", result));
    }

    @PostMapping("/getprofile")
    public ResponseEntity<?> getProfile(@RequestBody Map<String, String> requestBody) {
        String name = requestBody.get("name");
        if (name == null || name.trim().isEmpty()) {
            return ResponseEntity.badRequest().body("Name is required");
        }

        String profile = adminGetProfileService.get(name.trim());
        if (profile == null) {
            return ResponseEntity.status(404).body("Profile not found for user: " + name);
        }
        return ResponseEntity.ok(profile);
    }

}
