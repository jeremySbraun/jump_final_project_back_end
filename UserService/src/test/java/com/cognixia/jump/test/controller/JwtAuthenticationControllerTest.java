package com.cognixia.jump.test.controller;

import static org.hamcrest.CoreMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.rmi.server.ServerCloneException;
import java.util.Arrays;
import java.util.List;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;

import com.cognixia.jump.springcloud.controller.JwtAuthenticationController;
import com.cognixia.jump.springcloud.controller.UserController;
import com.cognixia.jump.springcloud.model.UserModel;
import com.cognixia.jump.springcloud.model.UserModel.Role;
import com.cognixia.jump.springcloud.service.UserService;

@ExtendWith(SpringExtension.class)
@WebMvcTest(JwtAuthenticationController.class)
public class JwtAuthenticationControllerTest {

	
	private final String STARTING_URIString = "http://localhost:8080";
	
	@Autowired
	private MockMvc mvc;
	
	
	@MockBean
	private UserService service;
	
	
	@InjectMocks
	private JwtAuthenticationController controller;
	
	@Test
	void registerTest() throws Exception{
		
		String uri = STARTING_URIString + "/register";
		
		
		//int id, @NotBlank String username, @NotBlank String password, @NotBlank String firstName,
		//@NotBlank String email, @NotBlank @Min(0) @Max(1) Role role
		UserModel user= new UserModel(1, "Test", "Test", "Joe", "test@gmail.com",
				Role.ROLE_USER);
		
		String userJson = user.toJson(); 
		
		
		when(service.save((UserModel) any(UserModel.class))).thenReturn(user);
		
		mvc.perform(post(uri).content(userJson)
				.contentType(MediaType.APPLICATION_JSON_VALUE))
		.andDo(print()).andExpect(status().isCreated());
		
		
	}
	
	void loadUserByName() throws Exception{
		
		String uriString = STARTING_URIString + "/authenticate";
		
		String usernameString = "Test";
		UserModel user = new UserModel(1, "Test", "Test", "Joe", "test@gmail.com",
				Role.ROLE_USER);
		
		List<SimpleGrantedAuthority> roles = 
				Arrays.asList(new SimpleGrantedAuthority(user.getRole().name()));
		
		when(service.loadUserByUsername(usernameString))
		.thenReturn(new org.springframework.security.core.userdetails.User(user.getUsername(), user.getPassword(),
				roles));
		
		mvc.perform(post(uriString).content("{\"username\" : " + user.getUsername()
				+ ", \"password\" : \"" + user.getPassword() + "\""+
				"}").contentType(MediaType.APPLICATION_JSON_VALUE))
		.andDo(print()).andExpect(status().isCreated());
		
	}
	
	
	
	
}
