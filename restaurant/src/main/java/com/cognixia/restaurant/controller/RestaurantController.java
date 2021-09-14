package com.cognixia.restaurant.controller;

import java.util.List;

import com.cognixia.restaurant.model.Review;
import com.cognixia.restaurant.model.Restaurant;
import com.cognixia.restaurant.repository.RestaurantRepository;
import com.cognixia.restaurant.repository.ReviewRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@CrossOrigin
@RestController
public class RestaurantController {

    @Autowired
    ReviewRepository reviewRepo;
    
    @Autowired
    RestaurantRepository restaurantRepo;

    // adds a restaurant
    @PostMapping(value = "/restaurant")
    public Restaurant save(@RequestBody Restaurant restaurant){
        Restaurant newRestaurant = restaurantRepo.save(restaurant);
        return newRestaurant;
    }

    // returns a list of all restaurants, with their list of reviews
    @GetMapping(value = "/restaurant")
    public ResponseEntity<?> all() {
        List<Restaurant> restaurants = restaurantRepo.findAll();
        
        for (Restaurant r : restaurants) {
            r.setReviews(reviewRepo.findAllByRestaurantId(r.getRestaurantId()));
        }

        return ResponseEntity.ok(restaurants);
    }

    // returns Restaurant by restaurant id
    @GetMapping(value = "/restaurant/{restaurantId}")
    public Restaurant findByRestaurantId(@PathVariable Integer restaurantId){
        Restaurant foundRestaurant = restaurantRepo.findByRestaurantId(restaurantId);
        foundRestaurant.setReviews(reviewRepo.findAllByRestaurantId(restaurantId));
        return foundRestaurant;
    }

    // updates restaurant
    @PutMapping(value = "/restaurant")
    public Restaurant update(@RequestBody Restaurant restaurant){
        return restaurantRepo.save(restaurant);
    }

    // deletes restaurant
    @DeleteMapping(value = "/restaurant/{restaurantId}")
    public void delete(@PathVariable Integer restaurantId){
        restaurantRepo.deleteById(restaurantId);
    }


}
