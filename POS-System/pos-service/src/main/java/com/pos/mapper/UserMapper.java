package com.pos.mapper;

import com.pos.entity.User;
import com.pos.payload.dto.UserDto;

public class UserMapper {

	public static UserDto toDto(User savedUser) {

		UserDto userDto = new UserDto();
		userDto.setId(savedUser.getId());
		userDto.setEmail(savedUser.getEmail());
		userDto.setRole(savedUser.getRole());
		userDto.setCreatedAt(savedUser.getCreatedAt());
		userDto.setLastLogin(savedUser.getLastLogin());
		userDto.setUpdatedAt(savedUser.getUpdatedAt());
		userDto.setPhone(savedUser.getPhone());
		userDto.setFullname(savedUser.getFullname());

		return userDto;
	}

}
