package com.pos.configuration;

import java.util.Arrays;
import java.util.Collections;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

	private final JwtValidator jwtValidator;

	public SecurityConfig(JwtValidator jwtValidator) {
		this.jwtValidator = jwtValidator;
	}

	@Bean
	public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {

		return http.sessionManagement(management -> management.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
				.authorizeHttpRequests(authorize -> authorize.requestMatchers("/api/**").authenticated()
						.requestMatchers("api/super-admin/**").hasRole("ADMIN").anyRequest().permitAll())
				.addFilterBefore(jwtValidator, BasicAuthenticationFilter.class).csrf(AbstractHttpConfigurer::disable)
				.cors(cors -> cors.configurationSource(corsConfigurationSource())).build();
	}

	private CorsConfigurationSource corsConfigurationSource() {
		return request -> {
			CorsConfiguration corsConfiguration = new CorsConfiguration();
			corsConfiguration.setAllowedOrigins(Arrays.asList("http://localhost:3000"));
			corsConfiguration.setAllowedMethods(Collections.singletonList("*"));
			corsConfiguration.setAllowCredentials(true);
			corsConfiguration.setAllowedHeaders(Collections.singletonList("*"));
			corsConfiguration.setExposedHeaders(Arrays.asList("Authorization"));
			corsConfiguration.setMaxAge(3600L);
			return corsConfiguration;
		};
	}

	@Bean
	public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}

}
