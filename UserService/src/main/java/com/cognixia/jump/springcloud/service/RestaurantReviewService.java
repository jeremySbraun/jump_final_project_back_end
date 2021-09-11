package com.cognixia.jump.springcloud.service;

import java.util.List;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

import com.cognixia.jump.springcloud.model.Restaurant;
import com.cognixia.jump.springcloud.model.Review;



@Service
@FeignClient(name = "ec2-44-193-200-36.compute-1.amazonaws.com")
public interface RestaurantReviewService {

	
	@PostMapping(value = "/restaurant")
    public Restaurant save(@RequestBody Restaurant restaurant);

    // returns a list of all restaurants, with their list of reviews
    @GetMapping(value = "/restaurant")
    public Iterable<Restaurant> all(); 
    // returns Restaurant by restaurant id
    @GetMapping(value = "/restaurant/{restaurantId}")
    public Restaurant findByRestaurantId(@PathVariable Integer restaurantId);

    // updates restaurant
    @PutMapping(value = "/restaurant")
    public Restaurant update(@RequestBody Restaurant restaurant);

    // deletes restaurant
    @DeleteMapping(value = "/restaurant/{restaurantId}")
    public void delete(@PathVariable Integer restaurantId);
    


    @PostMapping(value = "/restaurant/review")
    public Review save(@RequestBody Review review);

    @GetMapping(value = "/review/{reviewId}")
    public Review findByReviewId(@PathVariable Integer reviewId);


    @GetMapping(value = "review/user/{userId}")
    public Iterable<Review> findByUserId(@PathVariable Integer userId);

    @PutMapping(value = "/review/restaurant")
    public Review update(@RequestBody Review review);



}
