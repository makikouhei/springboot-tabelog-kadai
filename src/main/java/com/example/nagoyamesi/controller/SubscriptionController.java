package com.example.nagoyamesi.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.nagoyamesi.service.StripeService;
import com.example.nagoyamesi.service.UserService;

import jakarta.servlet.http.HttpServletRequest;

@RestController
@RequestMapping("/subscription")
public class SubscriptionController {

    private final UserService userService;
    private final StripeService stripeService;

    public SubscriptionController(UserService userService, StripeService stripeService) {
        this.userService = userService;
        this.stripeService = stripeService;
    }

    @PostMapping("/create-session")
    public String createSubscriptionSession(HttpServletRequest request, @RequestParam("userId") String userId) {
        return stripeService.createStripeSession(request, userId);
    }

    @GetMapping("/success")
    public String subscriptionSuccess(@RequestParam("session_id") String sessionId) {
        // Here you should fetch the session and verify it, also update the user role in DB
        userService.updateUserRole(sessionId, "ROLE_PREMIUM");
        return "Subscription successful and role updated.";
    }

    @GetMapping("/cancel")
    public String subscriptionCancel() {
        return "Subscription canceled.";
    }
}
