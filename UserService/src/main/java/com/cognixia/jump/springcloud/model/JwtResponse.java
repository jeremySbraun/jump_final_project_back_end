package com.cognixia.jump.springcloud.model;

import java.io.Serializable;

public class JwtResponse implements Serializable {

	
	
	// We will be storing our JWT that will be sent back
	// to the client assuming they are an authorized user
	private static final long serialVersionUID = -8091879091924046844L;
	private final String jwttoken;

	public JwtResponse(String jwttoken) {
		this.jwttoken = jwttoken;
	}

	public String getToken() {
		return this.jwttoken;
	}
}