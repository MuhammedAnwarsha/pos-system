package com.pos.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import com.pos.configuration.JwtProvider;
import com.pos.entity.User;
import com.pos.exception.ResourceNotFoundException;
import com.pos.exception.UserException;
import com.pos.repository.UserRepository;
import com.pos.service.UserService;

@Service
public class UserServiceImpl implements UserService {

	@Autowired
	private UserRepository userRepository;

	@Autowired
	private JwtProvider jwtProvider;

	@Override
	public User getUserFromJwtToken(String token) {

		String email = jwtProvider.getEmailFromToken(token);

		User user = userRepository.findByEmail(email);

		if (user == null) {
			throw new UserException("Invalid Token!");
		}
		return user;
	}

	@Override
	public User getCurrentUser() {

		String email = SecurityContextHolder.getContext().getAuthentication().getName();

		User user = userRepository.findByEmail(email);
		if (user == null) {
			throw new UserException("User not found");
		}
		return user;
	}

	@Override
	public User getUserByEmail(String email) {

		User user = userRepository.findByEmail(email);
		if (user == null) {
			throw new UserException("User not found");
		}
		return user;
	}

	@Override
	public User getUserById(Long id) {
		return userRepository.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("User not found with id: " + id));
	}

	@Override
	public List<User> getAllUsers() {
		return userRepository.findAll();
	}

}
