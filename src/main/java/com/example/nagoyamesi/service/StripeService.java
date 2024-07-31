package com.example.nagoyamesi.service;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.stripe.Stripe;
import com.stripe.exception.StripeException;
import com.stripe.model.checkout.Session;
import com.stripe.param.checkout.SessionCreateParams;

import jakarta.servlet.http.HttpServletRequest;

@Service
public class StripeService {

    @Value("${stripe.api-key}")
    private String stripeApiKey;

    public String createStripeSession(HttpServletRequest httpServletRequest, String userId) {
        Stripe.apiKey = stripeApiKey;
        String requestUrl = new String(httpServletRequest.getRequestURL());

        SessionCreateParams params = SessionCreateParams.builder()
                .setMode(SessionCreateParams.Mode.SUBSCRIPTION)
                .setSuccessUrl(requestUrl.replaceAll("/", "") + "/login/reserved/success?session_id={CHECKOUT_SESSION_ID}")
                .setCancelUrl(requestUrl.replaceAll("/", "") + "/login/reserved/cancel")
                .addLineItem(
                    SessionCreateParams.LineItem.builder()
                        .setPrice("price")  
                        .setQuantity(1L)
                        .build()
                )
                .putMetadata("userId", userId)
                .putMetadata("roleName", "ROLE_PREMIUM")
                .build();

        try {
            Session session = Session.create(params);
            return session.getId();
        } catch (StripeException e) {
            e.printStackTrace();
            return "";
        }
    }
}
