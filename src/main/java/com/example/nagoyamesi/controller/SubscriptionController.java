package com.example.nagoyamesi.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.nagoyamesi.entity.User;
import com.example.nagoyamesi.security.UserDetailsImpl;
import com.example.nagoyamesi.service.StripeService;

import jakarta.servlet.http.HttpServletRequest;

@Controller
@RequestMapping("/subscription")
public class SubscriptionController {

	@Autowired
	private StripeService stripeService;
		
	@GetMapping("/register")
	public String index(HttpServletRequest httpServletRequest, @AuthenticationPrincipal UserDetailsImpl userDetailsImpl, Model model) {
	    User user = userDetailsImpl.getUser(); 
	    String sessionId = stripeService.createStripeSession(httpServletRequest, user);
	    model.addAttribute("sessionId", sessionId);
	    return "subscription/register";
	}

	

}
