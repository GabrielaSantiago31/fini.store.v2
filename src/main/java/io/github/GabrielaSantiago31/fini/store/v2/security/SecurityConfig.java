package io.github.GabrielaSantiago31.fini.store.v2.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.access.expression.WebExpressionAuthorizationManager;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import lombok.extern.log4j.Log4j2;

@Configuration
@EnableWebSecurity
@Log4j2
public class SecurityConfig {
	
	@Autowired
	  SecurityFilter securityFilter;

	  @Bean
	  SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity) throws Exception {
		log.info("Verifying authentication and authorizations");
		
	    return httpSecurity
	        .csrf(csrf -> csrf.disable())
	        .sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
	        .authorizeHttpRequests(authorize -> authorize
	            .requestMatchers(HttpMethod.POST, "/finistore/home/login").permitAll()
	            .requestMatchers(HttpMethod.POST, "/finistore/home/register").permitAll()
	            .requestMatchers("/finistore/address/**").hasRole("ADMIN")
	            .requestMatchers(HttpMethod.GET, "/finistore/user/**").hasRole("ADMIN")
	            .requestMatchers(HttpMethod.POST, "/finistore/user").hasRole("USER")
	            .requestMatchers(HttpMethod.PUT, "/finistore/user/{id}" ).access(new WebExpressionAuthorizationManager("@webSecurity.checkUserId(authentication,#id)"))
	            .requestMatchers("/finistore/order/**").permitAll()
	            .requestMatchers("/finistore/payment/**").permitAll()
	            .requestMatchers(HttpMethod.GET,"/finistore/product/**").hasAnyRole("USER","ADMIN")
	            .requestMatchers(HttpMethod.POST,"/finistore/product").hasRole("ADMIN")
	            .requestMatchers(HttpMethod.PUT,"/finistore/product").hasRole("ADMIN")
	            .requestMatchers("/finistore/stock").hasRole("ADMIN")
	            .anyRequest().authenticated())
	        .addFilterBefore(securityFilter, UsernamePasswordAuthenticationFilter.class)
	        .build();
	  }

	  @Bean
	  AuthenticationManager authenticationManager(AuthenticationConfiguration authenticationConfiguration)
	      throws Exception {
	    return authenticationConfiguration.getAuthenticationManager();
	  }

	  @Bean
	  PasswordEncoder passwordEncoder() {
	    return new BCryptPasswordEncoder();
	  }
	
}
