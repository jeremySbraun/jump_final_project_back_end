package com.cognixia.restaurant.repository;

import com.cognixia.restaurant.model.Restaurant;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RestaurantRepository extends JpaRepository<Restaurant, Integer>{

    Restaurant findByRestaurantId(Integer restaurantId);
    
}
