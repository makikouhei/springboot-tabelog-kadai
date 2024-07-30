package com.example.nagoyamesi.service;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.stripe.Stripe;

import jakarta.servlet.http.HttpServletRequest;

@Service
public class StripeService {
	@Value("${stripe.api-key}")
    private String stripeApiKey;
	
	public String createStripeSession(HttpServletRequest httpServletRequest) {
		Stripe.apiKey = stripeApiKey;
        String requestUrl = new String(httpServletRequest.getRequestURL());
        
        SessionCreateParams params = SessionCreateParams.builder()
        		.setMode(SessionCreateParams.Mode.SUBSCRIPTION)
	}

}
