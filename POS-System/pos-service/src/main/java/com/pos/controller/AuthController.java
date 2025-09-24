package com.pos.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.pos.exception.UserException;
import com.pos.payload.dto.UserDto;
import com.pos.payload.response.AuthResponse;
import com.pos.service.AuthService;

@RestController
@RequestMapping("/auth")
public class AuthController {

	@Autowired
	private AuthService authService;

	@PostMapping("/signup")
	public ResponseEntity<AuthResponse> signup(@RequestBody UserDto userDto) throws UserException {
		return ResponseEntity.ok(authService.signup(userDto));
	}

	@PostMapping("/login")
	public ResponseEntity<AuthResponse> login(@RequestBody UserDto userDto) throws UserException {
		return ResponseEntity.ok(authService.login(userDto));
	}

}
