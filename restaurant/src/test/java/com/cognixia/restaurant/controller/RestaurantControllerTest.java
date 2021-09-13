package com.cognixia.restaurant.controller;


import static org.hamcrest.CoreMatchers.any;
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
import com.cognixia.restaurant.repository.RestaurantRepository;
import com.fasterxml.jackson.core.sym.Name;

@ExtendWith(SpringExtension.class)
@WebMvcTest(RestaurantController.class)
public class RestaurantControllerTest {

	private final String STARTING_URI = "http://localhost:6060";
	
	
	@Autowired
	private MockMvc mvc;
	
	
	
	@MockBean
	private RestaurantRepository serviceRepository;
	
	@InjectMocks
	private RestaurantController serviceController;
	
	@Test
	void testGetAllRestaurants() throws Exception{
		
		String uriString = STARTING_URI + "/restaurant";
		
		List<Restaurant> restaurants = Arrays.asList(
				new Restaurant(),
				new Restaurant(),
				new Restaurant());
		
		when(serviceRepository.findAll()).thenReturn(restaurants);
		
		
		mvc.perform(get(uriString))
		.andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
		.andExpect(jsonPath("$.length()").value(restaurants.size()))
		.andExpect(jsonPath("$[0].id").value(restaurants.get(0).getRestaurantId()))
		.andExpect(jsonPath("$[1].id").value(restaurants.get(1).getRestaurantId()))
		.andExpect(jsonPath("$[2].id").value(restaurants.get(2).getRestaurantId()));
				
		
	}
	
	
	@Test
	void testFindRestaurant() throws Exception{
		String uriString = STARTING_URI + "/restaurant";
		
		List<Restaurant> restaurants = Arrays.asList(
				new Restaurant());
		
		
		/*
		when(serviceRepository.findByName("Test")).thenReturn(restaurants);
		*/
		
		mvc.perform(get(uriString,"Test")).andExpect(content()
				.contentType(MediaType.APPLICATION_JSON_VALUE))
		.andExpect(jsonPath("$[0].id").value(restaurants.get(0).getRestaurantId()));
		
		
		
	}
	
	@Test
	void testAddRestaurant() throws Exception{
		String uriString =STARTING_URI + "/restaurant";
		
		Restaurant restaurant = new Restaurant();
		
		String restaurantJson = restaurant.toJson();
		
		when(serviceRepository.save(null)).thenReturn(restaurant);
		
		mvc.perform(post(uriString).content(restaurantJson)
				.contentType(MediaType.APPLICATION_JSON_VALUE))
		.andExpect(status().isCreated());
		
	}
	
	
	//Update but needs to update
	@Test
	void testUpdateRestaurant() throws Exception{
		String uriString = STARTING_URI+ "/restaurant";
		
		int testId = 1;
		
		Restaurant restaurant = 
				new Restaurant();
		
		String restaurantJson = restaurant.toJson();
		
		when(serviceRepository.save(null)).thenReturn(restaurant);
		
		mvc.perform(post(uriString).content(restaurantJson)
				.contentType(MediaType.APPLICATION_JSON_VALUE))
		.andExpect(status().isCreated());
	}
	
	//Delete Restaurant
	
	void testDeleteRestaurant() throws Exception{
		String uriString = STARTING_URI + "/restaurant/{restaurantId}";
		
		int testId = 1;
		
		Restaurant restaurant = new Restaurant();
		
		
		doNothing().when(serviceRepository).deleteById(testId);
		
		mvc.perform(delete(uriString));
	
	}
	
	
}
