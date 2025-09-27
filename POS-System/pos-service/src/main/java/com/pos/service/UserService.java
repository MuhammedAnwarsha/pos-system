package com.pos.service;

import java.util.List;

import com.pos.entity.User;

public interface UserService {

	User getUserFromJwtToken(String token);

	User getCurrentUser();

	User getUserByEmail(String email);

	User getUserById(Long id);

	List<User> getAllUsers();
}
