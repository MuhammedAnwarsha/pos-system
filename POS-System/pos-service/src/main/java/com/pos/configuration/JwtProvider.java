package com.pos.configuration;

import java.util.Collection;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import javax.crypto.SecretKey;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.stereotype.Component;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;

@Component
public class JwtProvider {

	private final JwtConfig jwtConfig;
	private final SecretKey key;

	public JwtProvider(JwtConfig jwtConfig) {
		this.jwtConfig = jwtConfig;
		this.key = Keys.hmacShaKeyFor(jwtConfig.getJwtSecret().getBytes());
	}

	public SecretKey getKey() {
		return key;
	}

	public String generateToken(Authentication authentication) {

		Collection<? extends GrantedAuthority> authorities = authentication.getAuthorities();
		String roles = populateAuthorities(authorities);

		return Jwts.builder().issuedAt(new Date()).expiration(new Date(new Date().getTime() + 86400000))
				.claim("email", authentication.getName()).claim("authorities", roles).signWith(key).compact();
	}

	private String populateAuthorities(Collection<? extends GrantedAuthority> authorities) {

		Set<String> auths = new HashSet<>();
		for (GrantedAuthority authority : authorities) {
			auths.add(authority.getAuthority());
		}
		return String.join(",", auths);
	}

	public String getEmailFromToken(String jwt) {

		jwt = jwt.substring(7);
		Claims claims = Jwts.parser().verifyWith(key).build().parseSignedClaims(jwt).getPayload();
		return String.valueOf(claims.get("email"));
	}

}
