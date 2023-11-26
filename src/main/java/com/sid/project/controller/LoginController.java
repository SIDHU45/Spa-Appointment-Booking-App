package com.sid.project.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import com.sid.project.entity.User;
import com.sid.project.service.UserService;

import jakarta.validation.Valid;

@Controller
public class LoginController {	
	
	private UserService userService;
	
	public LoginController(UserService theUserService) {
		userService = theUserService;
	}
		
	@GetMapping("/login")
	public String showLoginPage() {
		return "process/login-form";
	}
	
	@GetMapping("/signup_page")
	public String showSignUpPage(Model theModel) {
		theModel.addAttribute("user" , new User());
		return "process/signup-form";
	}
	@PostMapping("/register")
	public String registerUser(@Valid @ModelAttribute ("user") User theUser , BindingResult result) {
		
		if(result.hasErrors()) {
			return "process/signup-form";
		}else {
			userService.createUser(theUser);
			return "redirect:/login";
		}
	}	
	
	@GetMapping("/access-denied-page")
	public String showAccessDenied() {
		return "process/access-denied";
	}
}
