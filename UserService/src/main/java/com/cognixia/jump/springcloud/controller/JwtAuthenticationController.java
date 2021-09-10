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


@RestController
@CrossOrigin
public class JwtAuthenticationController {

	@Autowired
	private AuthenticationManager authenticationManager;

	@Autowired
	private JwtTokenUtil jwtTokenUtil;

	@Autowired
	private UserService userService;
	
	
	

	@RequestMapping(value = "/authenticate", method = RequestMethod.POST)
	public ResponseEntity<?> createAuthenticationToken(@RequestBody JwtRequest authenticationRequest) throws Exception {
		
	authenticate(authenticationRequest.getUsername(), authenticationRequest.getPassword());
		
	System.out.println("We made it through!");
	
		final UserDetails userDetails = userService
				.loadUserByUsername(authenticationRequest.getUsername());

		System.out.println("We made it through!");
		userDetails.getUsername();
		final String token = jwtTokenUtil.generateToken(userDetails);

		return ResponseEntity.ok(new JwtResponse(token));
	}

	private boolean authenticate(String username, String password) throws Exception {

		try {
			
			authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(username, password));
			return true;
		} catch (DisabledException e) {
			throw new Exception("USER_DISABLED", e);
		} catch (BadCredentialsException e) {
			throw new Exception("INVALID_CREDENTIALS", e);
		}
	}
	
	@RequestMapping(value = "/register", method = RequestMethod.POST)
	public ResponseEntity<?> saveUser(@RequestBody UserModel user) throws Exception{
		System.out.println("USERNAME" + user.getFirstName());
		return ResponseEntity.ok(userService.save(user));
	}
}
