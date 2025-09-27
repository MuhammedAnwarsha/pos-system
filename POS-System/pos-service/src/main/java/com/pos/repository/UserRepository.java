package com.pos.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.pos.entity.User;

public interface UserRepository extends JpaRepository<User, Long> {

	User findByEmail(String email);
}
