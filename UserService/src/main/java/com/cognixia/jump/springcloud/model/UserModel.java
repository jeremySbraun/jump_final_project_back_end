package com.cognixia.jump.springcloud.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;




@Entity
@Table(name = "user")
public class UserModel implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;



	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	 Integer id;
	
	@Column
	@NotBlank
	String username;
	
	
	@Column
	@NotBlank
	 String password;
	
	
	@Column
	@NotBlank
	 String firstName;
	
	
	@Column
	@NotBlank
	 String email;
	
	@Enumerated(EnumType.STRING)
	@Column
	Role role;
	
	public enum Role {
		ROLE_USER, ROLE_ADMIN	
	}

	
	
	
	
	public UserModel() {
		this(-1, "N/A", "N/A", "N/A", "N/A", Role.ROLE_USER);
	}



	public UserModel(int id, @NotBlank String username, @NotBlank String password, @NotBlank String firstName,
			@NotBlank String email, @NotBlank @Min(0) @Max(1) Role role) {
		super();
		this.id = id;
		this.username = username;
		this.password = password;
		this.firstName = firstName;
		this.email = email;
		this.role = role;
	}



	public int getId() {
		return id;
	}



	public void setId(int id) {
		this.id = id;
	}



	public String getUsername() {
		return username;
	}



	public void setUsername(String username) {
		this.username = username;
	}



	public String getPassword() {
		return password;
	}



	public void setPassword(String password) {
		this.password = password;
	}



	public String getFirstName() {
		return firstName;
	}



	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}



	public String getEmail() {
		return email;
	}



	public void setEmail(String email) {
		this.email = email;
	}



	public Role getRole() {
		return role;
	}



	public void setRole(Role role) {
		this.role = role;
	}



	@Override
	public String toString() {
		return "UserModel [id=" + id + ", username=" + username + ", password=" + password + ", firstName=" + firstName
				+ ", email=" + email + ", role=" + role + "]";
	}
	
	public String toJson() {
		
		return "{\"id\" : " + id
				+ ", \"username\" : \"" + username + "\""
				+ ", \"password\" : \"" + password + "\"" 
				+ ", \"firstName\" : \"" + firstName + "\"" 
				+ ", \"email\" : \"" + email + "\"" 
				+ ", \"role\" : \"" + role + "\"" +
		"}";

	}
	
	
	
	
}
