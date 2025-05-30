package com.ecommerce.ecommerce.entities;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "deliverer")  // corrected annotation attribute from 'collation' to 'collection'
public class Deliverer {

    @Id
    private String id;

    private String name;
    private String email;
    private String password;
    private String address;
    private String role;
    private String mobile;
    private String image;

    public String getImage() {
		return image;
	}
	public void setImage(String image) {
		this.image = image;
	}
	// Getters and Setters
    public String getId() { return id; }
    public void setId(String id) { this.id = id; }

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }

    public String getPassword() { return password; }
    public void setPassword(String password) { this.password = password; }

    public String getAddress() { return address; }
    public void setAddress(String address) { this.address = address; }

    public String getRole() { return role; }
    public void setRole(String role) { this.role = role; }

    public String getMobile() { return mobile; }
    public void setMobile(String mobile) { this.mobile = mobile; }
}
