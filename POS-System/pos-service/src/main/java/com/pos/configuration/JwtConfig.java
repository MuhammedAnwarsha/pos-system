package com.pos.configuration;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

@Configuration
public class JwtConfig {

	@Value("${jwt.secret}")
	private String jwtSecret;

	@Value("${jwt.header}")
	private String jwtHeader;

	public String getJwtSecret() {
		return jwtSecret;
	}

	public String getJwtHeader() {
		return jwtHeader;
	}

}
