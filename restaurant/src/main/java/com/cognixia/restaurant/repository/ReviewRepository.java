package com.cognixia.restaurant.repository;

import java.util.List;

import com.cognixia.restaurant.model.Review;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ReviewRepository extends JpaRepository<Review, Integer>{

    List<Review> findAllByRestaurantId(Integer restaurantId);

    List<Review> findAllByUserId(Integer userId);

    Review findByReviewId(Integer reviewId);
    
}
