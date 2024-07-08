package com.example.nagoyamesi.controller;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.nagoyamesi.entity.Restaurant;
import com.example.nagoyamesi.repository.RestaurantRepository;

@Controller
@RequestMapping("/restaurants")
public class RestaurantController {
	private final RestaurantRepository restaurantRepository;
	
	public RestaurantController(RestaurantRepository restaurantRepository) {
		this.restaurantRepository = restaurantRepository;
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
     public String show(@PathVariable("restaurantId") Integer restaurantId, Model model) {
		 Restaurant restaurant = restaurantRepository.getReferenceById(restaurantId);
         
         model.addAttribute("restaurant", restaurant);         
         
         return "restaurants/show";
     } 

}
