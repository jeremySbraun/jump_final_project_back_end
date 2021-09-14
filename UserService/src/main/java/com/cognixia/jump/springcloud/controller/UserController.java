package com.cognixia.jump.springcloud.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.cognixia.jump.springcloud.model.Restaurant;
import com.cognixia.jump.springcloud.model.Review;
import com.cognixia.jump.springcloud.service.RestaurantReviewService;
import com.fasterxml.jackson.annotation.JacksonInject.Value;

@CrossOrigin
@RestController
public class UserController {

	@Autowired
	RestaurantReviewService restrevServ;

	
	
	
	//Allows the admin to update the restaurant information
	@PutMapping(value = "/admin/update")
	public Restaurant updateRestaurant(@RequestBody Restaurant restaurant) {
		Restaurant savedRestaurant = restrevServ.update(restaurant);
		return savedRestaurant;
		
	}
	
	//Allows the user to create a review or rate a restaurant 
	@PostMapping(value = "/user/update")
	public Review writeReview(@RequestBody Review review) {
		Review newReview = restrevServ.save(review);
		
		return newReview;
		
	}
	
	
	
}
