package com.example.nagoyamesi.controller;

import java.util.ArrayList;
import java.util.List;

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
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.example.nagoyamesi.entity.Reservation;
import com.example.nagoyamesi.entity.Restaurant;
import com.example.nagoyamesi.entity.User;
import com.example.nagoyamesi.form.ReservationInputForm;
import com.example.nagoyamesi.form.ReservationRegisterForm;
import com.example.nagoyamesi.repository.ReservationRepository;
import com.example.nagoyamesi.repository.RestaurantRepository;
import com.example.nagoyamesi.security.UserDetailsImpl;

@Controller
public class ReservationController {
	private final ReservationRepository reservationRepository; 
	private final RestaurantRepository restaurantRepository;
    
    public ReservationController(ReservationRepository reservationRepository, RestaurantRepository restaurantRepository) {        
        this.reservationRepository = reservationRepository;
        this.restaurantRepository = restaurantRepository;
    }    
    @GetMapping("/reservations")
    public String index(@AuthenticationPrincipal UserDetailsImpl userDetailsImpl, @PageableDefault(page = 0, size = 10, sort = "id", direction = Direction.ASC) Pageable pageable, Model model) {
        User user = userDetailsImpl.getUser();
        Page<Reservation> reservationPage = reservationRepository.findByUserOrderByCreatedAtDesc(user, pageable);
        
        model.addAttribute("reservationPage", reservationPage);         
        
        return "reservations/index";
    }
    
    @GetMapping("restaurants/{restaurantId}/reservations/register")
    public String show(@PathVariable( "restaurantId") Integer restaurantId, Model model) {
    	Restaurant restaurant = restaurantRepository.getReferenceById(restaurantId);
    	List<String> times = new ArrayList<>();
    	for (int hour = 0; hour < 24; hour++) {
    	    times.add(String.format("%02d:00", hour));
    	    times.add(String.format("%02d:30", hour));
    	}
    	
    	model.addAttribute("times", times);
        model.addAttribute("restaurant", restaurant);  
         model.addAttribute("reservationInputForm", new ReservationInputForm());
        
        return "reservations/register";
    }
    
    @GetMapping("/restaurants/{restaurantId}/reservations/input")
	public String input(@PathVariable("restaurantId") Integer restaurantId,
			@ModelAttribute @Validated ReservationInputForm reservationInputForm,
			BindingResult bindingResult,
			RedirectAttributes redirectAttributes,
			Model model) {
    	
    	Restaurant restaurant = restaurantRepository.getReferenceById(restaurantId);
    	/*String ReservationDate = reservationInputForm.getFromReservationDate();
        String fromReservationTime = reservationInputForm.getFromReservationTime();
        Integer numberOfPeople = reservationInputForm.getNumberOfPeople();*/
        
        if (bindingResult.hasErrors()) {
        	model.addAttribute("restaurant", restaurant);
			model.addAttribute("errorMessage", "予約内容に誤りがあります。");
			return "restaurants/register";
		}
        redirectAttributes.addFlashAttribute("reservationInputForm", reservationInputForm);

		return "redirect:/restaurants/{restaurantId}/reservations/confirm";

    
    }
    
    @GetMapping("/restaurants/{restaurantId}/reservations/confirm")
	public String confirm(@PathVariable("restaurantId") Integer restaurantId,
			@ModelAttribute ReservationInputForm reservationInputForm,
			@AuthenticationPrincipal UserDetailsImpl userDetailsImpl,
			Model model) {
		Restaurant restaurant = restaurantRepository.getReferenceById(restaurantId);
		User user = userDetailsImpl.getUser();
		

		ReservationRegisterForm reservationRegisterForm = new ReservationRegisterForm(
		        restaurant.getId(), 
		        user.getId(),
		        reservationInputForm.getReservationDate(),
		        reservationInputForm.getReservationTime(),
		        reservationInputForm.getNumberOfPeople()
		    );

		model.addAttribute("restaurant", restaurant);
		model.addAttribute("reservationRegisterForm", reservationRegisterForm);

		return "reservations/confirm";
	}
}
