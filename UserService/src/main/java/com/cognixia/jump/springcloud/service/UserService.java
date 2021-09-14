package com.cognixia.jump.springcloud.service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.config.core.GrantedAuthorityDefaults;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.cognixia.jump.springcloud.model.UserModel;
import com.cognixia.jump.springcloud.repository.UserRepository;



@Service
public class UserService implements UserDetailsService {

	
	@Autowired
	private UserRepository userRepo;
	
	@Autowired
	private PasswordEncoder bEncoder;
	
	
	
	//Needed to check if a user is in the database
	// because find by user name 
	// is returning a list of users
	// we create a user of the first indexed object
	//
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
			
		System.out.println(username + " IS RIGHT HERE!!!!");
		
		List<SimpleGrantedAuthority> roles = null;
		List<UserModel> users = userRepo.findByUsername(username);
		
		UserModel user = users.get(0);
		
		
		if (user == null) {
			System.out.println("User not found");
			throw new UsernameNotFoundException("User not found with username: " + username);
		}
		
		
		roles = Arrays.asList(new SimpleGrantedAuthority(user.getRole().name()));
		return new org.springframework.security.core.userdetails.User(user.getUsername(), user.getPassword(),
				roles);
	}
	
	
	// We simple use the save method to register a new user
	// Any user added will 
	public UserModel save(UserModel user) {
		
		System.out.println("USERNAME" + user.getFirstName());
		UserModel addUser = new UserModel
				(-1, user.getUsername(), 
						bEncoder.encode(user.getPassword())
						, user.getFirstName(), user.getEmail(), user.getRole());
		System.out.println("USERNAME" + addUser.getFirstName());
		userRepo.save(addUser);
		return addUser;
		
	}
}
