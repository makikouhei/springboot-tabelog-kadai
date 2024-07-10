package com.example.nagoyamesi.controller;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.data.web.PageableDefault;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.nagoyamesi.entity.Restaurant;
import com.example.nagoyamesi.entity.Review;
import com.example.nagoyamesi.entity.User;
import com.example.nagoyamesi.repository.RestaurantRepository;
import com.example.nagoyamesi.repository.ReviewRepository;
import com.example.nagoyamesi.repository.UserRepository;
import com.example.nagoyamesi.security.UserDetailsImpl;
import com.example.nagoyamesi.service.ReviewService;

@Controller
@RequestMapping("/restaurants")
public class RestaurantController {
	private final RestaurantRepository restaurantRepository;
	private final ReviewRepository reviewRepository;
    private final UserRepository userRepository;
	private final ReviewService reviewService;

	
	public RestaurantController(RestaurantRepository restaurantRepository, ReviewRepository reviewRepository, UserRepository userRepository, ReviewService reviewService) {
		this.restaurantRepository = restaurantRepository;
		this.reviewRepository = reviewRepository;
        this.userRepository = userRepository;
        this.reviewService = reviewService;
	}
	
	 @GetMapping
     public String index( Model model, @PageableDefault(page = 0, size = 10, sort = "id", direction = Direction.ASC) Pageable pageable, @RequestParam(name = "keyword", required = false) String keyword) {
         Page<Restaurant> restaurantPage;
                 
         if (keyword != null && !keyword.isEmpty()) {
        	 restaurantPage = restaurantRepository.findByNameLike("%" + keyword + "%", pageable);
         
         } else {
        	 restaurantPage = restaurantRepository.findAll(pageable);
         }                
         
         model.addAttribute("restaurantPage", restaurantPage);
         model.addAttribute("keyword", keyword);
         
         return "restaurants/index";
     }
	 
	 @GetMapping("/{restaurantId}")
     public String show(@PathVariable("restaurantId") Integer restaurantId, Model model, @AuthenticationPrincipal UserDetailsImpl userDatailsImpl) {
		 Restaurant restaurant = restaurantRepository.getReferenceById(restaurantId);
		 List<Review> reviewList = reviewRepository.findTop4ByRestaurantOrderByCreatedAtDesc(restaurant);
	     long ReviewCount = reviewRepository.countByRestaurant(restaurant);

		User user = userDatailsImpl != null ? userRepository.getReferenceById(userDatailsImpl.getUser().getId()) : null;
	     model.addAttribute("hasReview", reviewService.hasReview(restaurant, user));
         model.addAttribute("restaurant", restaurant);  
         model.addAttribute("reviewList", reviewList);            
         model.addAttribute("ReviewCount", ReviewCount);
         
         return "restaurants/show";
     } 

}
