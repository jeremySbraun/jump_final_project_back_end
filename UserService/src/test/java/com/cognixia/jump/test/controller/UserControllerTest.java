package com.cognixia.jump.test.controller;

import static org.hamcrest.CoreMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;

import com.cognixia.jump.springcloud.controller.UserController;
import com.cognixia.jump.springcloud.model.Restaurant;
import com.cognixia.jump.springcloud.service.RestaurantReviewService;

@ExtendWith(SpringExtension.class)
@WebMvcTest(UserController.class)
public class UserControllerTest {

	
	private final String STARTING_URIString = "http://localhost:8080";
	
	
	@Autowired
	private MockMvc mvc;
	
	
	@MockBean
	private RestaurantReviewService restaurantReviewService;
	
	
	@InjectMocks
	private UserController controller;
	
	
	void updateRestaurantTest() throws Exception{
		String uriString = STARTING_URIString + "/admin/update";
		
		Restaurant restaurant = new Restaurant
				("Pizza Land", "123 Test Lane", "Pizza", "Get the food", null);
	
	
		when(restaurantReviewService.update((Restaurant) any(Restaurant.class))).thenReturn(restaurant);
	
		mvc.perform(put(uriString).content("{\"name\" : " + restaurant.getName()
		+ ", \"address\" : \"" + restaurant.getAddress() + "\""+
		", \"cuisine\" : \"" + restaurant.getCuisine() + "\""+
		", \"description\" : \"" + restaurant.getDescription() + "\""+
		", \"reviews\" : \"" + restaurant.getReviews() + "\""+
		"}").contentType(MediaType.APPLICATION_JSON_VALUE))
		.andReturn()
		;
		
	
	}
	
	
}
