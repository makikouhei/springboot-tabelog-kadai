package com.example.nagoyamesi.controller;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.data.web.PageableDefault;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.example.nagoyamesi.entity.Restaurant;
import com.example.nagoyamesi.entity.Review;
import com.example.nagoyamesi.entity.User;
import com.example.nagoyamesi.form.RestaurantRegisterForm;
import com.example.nagoyamesi.form.ReviewEditForm;
import com.example.nagoyamesi.form.ReviewRegisterForm;
import com.example.nagoyamesi.repository.RestaurantRepository;
import com.example.nagoyamesi.repository.ReviewRepository;
import com.example.nagoyamesi.security.UserDetailsImpl;
import com.example.nagoyamesi.service.ReviewService;

@Controller
@RequestMapping("/restaurants/{restaurantId}/reviews")
public class ReviewController {
	private final ReviewRepository reviewRepository;
	private final RestaurantRepository restaurantRepository;
	private final ReviewService reviewService;
	
	public ReviewController(ReviewRepository reviewRepository, RestaurantRepository restaurantRepository, ReviewService reviewService) {
		this.reviewRepository = reviewRepository;
		this.restaurantRepository = restaurantRepository;
		this.reviewService = reviewService;
		
	}
	
	@GetMapping
	public String index(@PathVariable("restaurantId") Integer restaurantId, Model model,
			@PageableDefault(page = 0, size = 10, sort = "id", direction = Direction.ASC) Pageable pageable) {
		Restaurant restaurant = restaurantRepository.getReferenceById(restaurantId);
		Page<Review> reviewPage = reviewRepository.findByRestaurantOrderByCreatedAtDesc(restaurant, pageable);

		model.addAttribute("reviewPage", reviewPage);
		model.addAttribute("restaurant", restaurant);

		return "reviews/index";
	}

	@GetMapping("/register")
	public String register(@PathVariable("restaurantId") Integer restaurantId, Model model) {
		Restaurant restaurant = restaurantRepository.getReferenceById(restaurantId);

		model.addAttribute("reviewRegisterForm", new RestaurantRegisterForm());
		model.addAttribute("restaurant", restaurant);

		return "review/register";
	}

	@PostMapping("/create")
	public String create(@PathVariable("restaurantId") Integer houseId,
			@ModelAttribute @Validated ReviewRegisterForm reviewRegisterForm, BindingResult bindingResult,
			RedirectAttributes redirectAttributes,
			@AuthenticationPrincipal UserDetailsImpl userDetailsImpl) {
		if (bindingResult.hasErrors()) {
			return "/review/register";
		}

		User user = userDetailsImpl.getUser();
		reviewService.create(user, reviewRegisterForm);
		redirectAttributes.addFlashAttribute("successMessage", "レビューを登録しました。");

		return "redirect:/restaurants/{houseId}";
	}

	@GetMapping("/{reviewId}/edit")
	public String edit(@PathVariable("reviewId") Integer reviewId, @PathVariable("restaurantId") Integer restaurantId,
			Model model) {
		Review review = reviewRepository.getReferenceById(reviewId);
		Restaurant restaurant = restaurantRepository.getReferenceById(restaurantId);

		ReviewEditForm reviewEditForm = new ReviewEditForm(review.getId(), review.getRating(), review.getComment());

		model.addAttribute("reviewEditForm", reviewEditForm);
		model.addAttribute("restaurant", restaurant);
		model.addAttribute("review", review);

		return "/review/edit";
	}

	@PostMapping("/{reviewid}/update")
	public String update(@PathVariable("reviewid") Integer reviewId,
			@ModelAttribute @Validated ReviewEditForm reviewEditForm, BindingResult bindingResult,
			RedirectAttributes redirectAttributes) {
		if (bindingResult.hasErrors()) {
			return "/review/edit";
		}

		reviewService.update(reviewEditForm);
		redirectAttributes.addFlashAttribute("successMessage", "レビューを編集しました。");

		return "redirect:/restaurants/{restaurantId}";
	}

	@PostMapping("/{reviewId}/delete")
	public String delete(@PathVariable("reviewId") Integer reviewId, RedirectAttributes redirectAttributes,
			Model model) {
		reviewRepository.deleteById(reviewId);

		redirectAttributes.addFlashAttribute("successMessage", "レビューを削除しました。");

		return "redirect:/restaurants/{restaurantId}";
	}
	
}