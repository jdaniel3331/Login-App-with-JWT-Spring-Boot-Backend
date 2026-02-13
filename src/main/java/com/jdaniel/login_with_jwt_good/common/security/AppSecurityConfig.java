package com.jdaniel.login_with_jwt_good.common.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class AppSecurityConfig {
	
	@Bean
	public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
		http
		    .csrf(csrf -> csrf.disable())
		    .cors(cors -> cors.disable())
		    .authorizeHttpRequests(auth -> auth
		    		.anyRequest().permitAll()
		    
		    );
		    
		return http.build();
	}

}
