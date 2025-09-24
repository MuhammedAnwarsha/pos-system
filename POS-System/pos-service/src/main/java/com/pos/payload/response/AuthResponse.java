package com.pos.payload.response;

import com.pos.payload.dto.UserDto;

public class AuthResponse {

	private String jwt;

	private String message;

	private UserDto user;

	public String getJwt() {
		return jwt;
	}

	public void setJwt(String jwt) {
		this.jwt = jwt;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public UserDto getUser() {
		return user;
	}

	public void setUser(UserDto user) {
		this.user = user;
	}

}
