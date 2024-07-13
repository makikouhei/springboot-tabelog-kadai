package com.example.nagoyamesi.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.nagoyamesi.repository.UserRepository;
import com.example.nagoyamesi.service.UserService;

@Controller
@RequestMapping("/subscription")
public class SubscriptionController {
	private final UserRepository userRepository;
    private final UserService userService;

    public SubscriptionController(UserRepository userRepository, UserService userService) {
        this.userRepository = userRepository;
        this.userService = userService;
    }

}
