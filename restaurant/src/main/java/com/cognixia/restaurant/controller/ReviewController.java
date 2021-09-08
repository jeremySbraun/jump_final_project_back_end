package com.cognixia.restaurant.controller;

import java.util.List;

import com.cognixia.restaurant.model.Review;
import com.cognixia.restaurant.repository.RestaurantRepository;
import com.cognixia.restaurant.repository.ReviewRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ReviewController {
    
    @Autowired
    private ReviewRepository reviewRepo;

    @Autowired
    private RestaurantRepository restaurantRepo;

    @PostMapping(value = "/restaurant/review")
    public Review save(@RequestBody Review review){
        Review newReview = reviewRepo.save(review);
        return newReview;
    }

    @GetMapping(value = "/review/{reviewId}")
    public Review findByReviewId(@PathVariable Integer reviewId){
        Review foundReview = reviewRepo.findByReviewId(reviewId);
        return foundReview;
    }

    @GetMapping(value = "review/restaurant/{restaurantId}")
    public Iterable<Review> findByRestaurantId(@PathVariable Integer restaurantId){
        List<Review> reviews = reviewRepo.findAllByRestaurantId(restaurantId);
        return reviews;
    }

    @GetMapping(value = "review/user/{userId}")
    public Iterable<Review> findByUserId(@PathVariable Integer userId){
        List<Review> reviews = reviewRepo.findAllByUserId(userId);
        return reviews;
    }

    @PutMapping(value = "/review/restaurant")
    public Review update(@RequestBody Review review){
        return reviewRepo.save(review);
    }

    @DeleteMapping(value = "/review/{reviewId}")
    public void delete(@PathVariable Integer reviewId){
        reviewRepo.deleteById(reviewId);
    }

}
