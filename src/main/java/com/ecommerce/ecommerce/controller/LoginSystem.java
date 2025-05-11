package com.ecommerce.ecommerce.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ecommerce.ecommerce.entities.User;
import com.ecommerce.ecommerce.services.ForgotPassword;
import com.ecommerce.ecommerce.services.Login;
import com.ecommerce.ecommerce.services.Register;

@RestController
@RequestMapping("/")
public class LoginSystem {
	
	@Autowired
	private Register register;
	
	@Autowired
	private Login login;
	
	@Autowired
	private ForgotPassword forgotPassword;
	
	@PostMapping("/login")
	public String login(@RequestBody User user) {
		if(user.getName()==null || user.getPassword()==null) {
			return "Please enter the username and password";
		}else {
			String res=login.login(user);
			return res;
		}
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
}
