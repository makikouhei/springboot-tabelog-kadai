package com.example.nagoyamesi.controller;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.example.nagoyamesi.entity.Restaurant;
import com.example.nagoyamesi.repository.RestaurantRepository;
import com.example.nagoyamesi.service.ReviewService;

@Controller
public class HomeController {
	private final RestaurantRepository restaurantRepository;
	private final ReviewService reviewService;
	
	public HomeController(RestaurantRepository restaurantRepository, ReviewService reviewService) {
		this.restaurantRepository = restaurantRepository;
		this.reviewService = reviewService;

	}
	
	@GetMapping("/")
    public String index(Model model) {
		Double averageRating = reviewService.getAverageRating();
		Long reviewCount = reviewService.getReviewCount();
        
        if (reviewCount == 0) {
            averageRating = null;
        }
        
        
		List<Restaurant>  restaurantList = restaurantRepository.findTop6ByOrderByCreatedAtDesc();
		model.addAttribute("averageRating", averageRating);
        model.addAttribute("reviewCount", reviewCount);
        model.addAttribute("restaurantList", restaurantList);        
        return "index";
    }   
}
