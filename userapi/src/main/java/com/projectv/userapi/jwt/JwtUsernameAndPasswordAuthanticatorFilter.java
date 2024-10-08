package com.projectv.userapi.jwt;

import java.io.IOException;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.time.temporal.TemporalUnit;
import java.util.Date;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.fasterxml.jackson.databind.ObjectMapper;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;

public class JwtUsernameAndPasswordAuthanticatorFilter extends UsernamePasswordAuthenticationFilter {

	private final AuthenticationManager authenticationManager;

	@Autowired
	public JwtUsernameAndPasswordAuthanticatorFilter(AuthenticationManager authenticationManager) {
		this.authenticationManager = authenticationManager;
	}

	@Override
	public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response)
			throws AuthenticationException {
		try {
			AuthRequest authRequest = new ObjectMapper().readValue(request.getInputStream(), AuthRequest.class);

			Authentication authentication = new UsernamePasswordAuthenticationToken(authRequest.getUsername(),
					authRequest.getPassword());

			Authentication authenticate = authenticationManager.authenticate(authentication);
			return authenticate;
		} catch (IOException e) {
			throw new RuntimeException();
		}
	}

	@Override
	protected void successfulAuthentication(HttpServletRequest request, HttpServletResponse response, FilterChain chain,
			Authentication authResult) throws IOException, ServletException {
		String key = "vaibhavkeyvaibhavkeyvaibhavkeyvaibhavkeyvaibhavkeyvaibhavkeyvaibhavkeyvaibhavkey";
		String token =  Jwts.builder()
		         .setSubject(authResult.getName())
		         .claim("authorities", authResult.getAuthorities())
		         .setIssuedAt(new Date())
		         .setExpiration(java.sql.Date.valueOf(LocalDate.now().plus(1L, ChronoUnit.DAYS)))
		         .signWith(Keys.hmacShaKeyFor(key.getBytes()))
		         .compact();

		
		response.addHeader("Authorization", "Bearer " + token);
	}

}
