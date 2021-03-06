package com.cognixia.jump.springcloud.controller;


import java.util.Objects;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.cognixia.jump.springcloud.config.JwtTokenUtil;
import com.cognixia.jump.springcloud.model.JwtRequest;
import com.cognixia.jump.springcloud.model.JwtResponse;
import com.cognixia.jump.springcloud.model.UserModel;
import com.cognixia.jump.springcloud.service.UserService;



//Cross origins allows us to 
@RestController
@CrossOrigin
public class JwtAuthenticationController {

	@Autowired
	private AuthenticationManager authenticationManager;

	@Autowired
	private JwtTokenUtil jwtTokenUtil;

	@Autowired
	private UserService userService;
	
	
	

	//authenticate will allow a user to ensure 
	// they are registered in the system. If they are not they will be denied access
	// If they are registered they will be given a JWT to access 
	// User/Admin Request mapping.
	
	@RequestMapping(value = "/authenticate", method = RequestMethod.POST)
	public ResponseEntity<?> createAuthenticationToken(@RequestBody JwtRequest authenticationRequest) throws Exception {
		
	authenticate(authenticationRequest.getUsername(), authenticationRequest.getPassword());
		
	
		final UserDetails userDetails = userService
				.loadUserByUsername(authenticationRequest.getUsername());

		userDetails.getUsername();
		final String token = jwtTokenUtil.generateToken(userDetails);

		return ResponseEntity.ok(new JwtResponse(token));
	}

	private boolean authenticate(String username, String password) throws Exception {

		try {
			
			
			// the username and password are provided
			// Will be checked against the JwtUserDetails Service
			// if the authentication is successful will return true. 
			authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(username, password));
			return true;
		} catch (DisabledException e) {
			throw new Exception("USER_DISABLED", e);
		} catch (BadCredentialsException e) {
			throw new Exception("INVALID_CREDENTIALS", e);
		}
	}
	
	
	
	//Allows us to create a new user; generally. A new user will always be 
	// Registered as a USER. An admin will have to be updated in the database
	
	@RequestMapping(value = "/register", method = RequestMethod.POST)
	public ResponseEntity<?> saveUser(@RequestBody UserModel user) throws Exception{
		System.out.println("USERNAME" + user.getFirstName());
		return ResponseEntity.ok(userService.save(user));
	}
}
