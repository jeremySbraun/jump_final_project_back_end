package com.cognixia.restaurant.controller;

import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.Arrays;
import java.util.List;

import org.hibernate.validator.internal.util.logging.formatter.CollectionOfObjectsToStringFormatter;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;

import com.cognixia.restaurant.model.Restaurant;
import com.cognixia.restaurant.model.Review;
import com.cognixia.restaurant.repository.ReviewRepository;

@ExtendWith(SpringExtension.class)
@WebMvcTest(ReviewController.class)
public class ReviewControllerTest {

	
	private final String STARTING_URI = "http://localhost:6060";
	
	@Autowired
	private MockMvc mvc;
	
	@MockBean
	private ReviewRepository service;
	
	@InjectMocks
	private ReviewController controller;
	
	void testFindByReviewId() throws Exception{
		int id = 1;
		
		Review review = new Review();
		
		String uriString = STARTING_URI + "/review/{reviewId}";
		
		when(service.findByReviewId(id)).thenReturn(review);
		
		mvc.perform(get(uriString, id)).andExpect(status().isFound())
		.andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
		.andExpect(jsonPath("$.id").value(review.getReviewId()))
		.andExpect(jsonPath("$.body").value(review.getBody()))
		.andExpect(jsonPath("$.time").value(review.getTime()))
		.andExpect(jsonPath("$.date").value(review.getDate()))
		.andExpect(jsonPath("$.rating").value(review.getRating()))
		.andExpect(jsonPath("$.restaurantId").value(review.getRestaurantId()))
		.andExpect(jsonPath("$.userId").value(review.getUserId()));
		
	}
	
	void testFindByRestaurantId() throws Exception{
		int id = 1;
		
		String uriString = STARTING_URI + "review/restaurant/{restaurantId}";
		
		List<Review> reviews= Arrays.asList(
				new Review (),
				new Review(),
				new Review()
				);
		
		when(service.findAllByRestaurantId(id)).thenReturn(reviews);
		
		mvc.perform(get(uriString, id))
		.andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
		.andExpect(jsonPath("$.length()").value(reviews.size()))
		.andExpect(jsonPath("$[0].id").value(reviews.get(0).getRestaurantId()))
		.andExpect(jsonPath("$[1].id").value(reviews.get(1).getRestaurantId()))
		.andExpect(jsonPath("$[2].id").value(reviews.get(2).getRestaurantId()));
		
	}
	
	void testFindByUserId() throws Exception{
		int id = 1;
		
		String uriString = STARTING_URI + "review/user/{userId}";
		
		
		List<Review> reviews= Arrays.asList(
				new Review (),
				new Review(),
				new Review()
				);
		
		when(service.findAllByRestaurantId(id)).thenReturn(reviews);
		
		mvc.perform(get(uriString, id))
		.andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
		.andExpect(jsonPath("$.length()").value(reviews.size()))
		.andExpect(jsonPath("$[0].id").value(reviews.get(0).getRestaurantId()))
		.andExpect(jsonPath("$[1].id").value(reviews.get(1).getRestaurantId()))
		.andExpect(jsonPath("$[2].id").value(reviews.get(2).getRestaurantId()));
		
		
	}
	
	@Test
	void testAddReview() throws Exception{
		String uriString =STARTING_URI + "/restaurant/review";
		
		Review review = new Review();
		
		String reviewJson = review.toJson();
		
		when(service.save(null)).thenReturn(review);
		
		mvc.perform(post(uriString).content(reviewJson)
				.contentType(MediaType.APPLICATION_JSON_VALUE))
		.andExpect(status().isCreated());
		
	}
	
	@Test
	void testUpdateReview() throws Exception{
		String uriString = STARTING_URI+ "/review/restaurant";
		
		int testId = 1;
		
		Review review = 
				new Review();
		
		String reviewJson = review.toJson();
		
		when(service.save(null)).thenReturn(review);
		
		mvc.perform(post(uriString).content(reviewJson)
				.contentType(MediaType.APPLICATION_JSON_VALUE))
		.andExpect(status().isCreated());
	}
	
	void testDeleteReview() throws Exception{
		String uriString = STARTING_URI + "/review/{reviewId}";
		
		int testId = 1;
		
		Review review = new Review();
	
		
		doNothing().when(service).deleteById(testId);
		
		mvc.perform(delete(uriString));
	
	}
	
	
}
