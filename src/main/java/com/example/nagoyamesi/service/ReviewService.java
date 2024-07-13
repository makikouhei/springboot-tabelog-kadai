package com.example.nagoyamesi.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.nagoyamesi.entity.Restaurant;
import com.example.nagoyamesi.entity.Review;
import com.example.nagoyamesi.entity.User;
import com.example.nagoyamesi.form.ReviewEditForm;
import com.example.nagoyamesi.form.ReviewRegisterForm;
import com.example.nagoyamesi.repository.RestaurantRepository;
import com.example.nagoyamesi.repository.ReviewRepository;

@Service
public class ReviewService {
	private final ReviewRepository reviewRepository;
	private final RestaurantRepository restaurantRepository;
	
	public ReviewService(ReviewRepository reviewRepository, RestaurantRepository restaurantRepository) {
		this.reviewRepository = reviewRepository;
		this.restaurantRepository = restaurantRepository;

	}
	
	@Transactional
    public void create(User user, ReviewRegisterForm reviewRegisterForm) {
		Review review = new Review();
		
		Restaurant restaurant = restaurantRepository.getReferenceById(reviewRegisterForm.getRestaurantId());
		review.setRating(reviewRegisterForm.getRating());                
		review.setComment(reviewRegisterForm.getComment());
		review.setRestaurant(restaurant);
		review.setUser(user);
		
		reviewRepository.save(review);
	}
	@Transactional
    public void update(ReviewEditForm reviewEditForm) {
        Review review = reviewRepository.getReferenceById(reviewEditForm.getId());        
        
        review.setRating(reviewEditForm.getRating());                
        review.setComment(reviewEditForm.getComment());
                    
        reviewRepository.save(review);
    }
	public boolean hasReview(Restaurant restaurant, User user) {
        if (user == null) {
            return false;
        }
        Review review = reviewRepository.findByRestaurantAndUser(restaurant, user);
        return review != null;
	}
	
	@Transactional(readOnly = true)
    public double getAverageRating() {
        return reviewRepository.getAverageRating() != null ? reviewRepository.getAverageRating() : 0.0;
    }

    @Transactional(readOnly = true)
    public long getReviewCount() {
        return reviewRepository.count();
    }

}
