package com.projectv.userapi.jwt;

import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collector;
import java.util.stream.Collectors;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.OncePerRequestFilter;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import lombok.experimental.var;

public class JwtTokenVerifierFilter extends OncePerRequestFilter{

	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
			throws ServletException, IOException {
		
		String authorizationHeader = request.getHeader("Authorization");
		if(Optional.ofNullable(authorizationHeader).isEmpty() || !authorizationHeader.startsWith("Bearer ")) {
            filterChain.doFilter(request, response);
            return;
		}
		String token = authorizationHeader.replaceAll("Bearer ", "");
		try {
			String secretkey = "vaibhavkeyvaibhavkeyvaibhavkeyvaibhavkeyvaibhavkeyvaibhavkeyvaibhavkeyvaibhavkey";
					
			// this is deprecated
		//	Jws<Claims> claimsJws = Jwts.parser().setSigningKey(Keys.hmacShaKeyFor(secretkey.getBytes())).parseClaimsJws(token);
			
			Jws<Claims> claimsJws =	Jwts.parserBuilder()
					.setSigningKey(Keys.hmacShaKeyFor(secretkey.getBytes()))
					.build()
					.parseClaimsJws(token);
			
			Claims body = claimsJws.getBody();
			
			String userName = body.getSubject();
			var authorities = (List<Map<String, String>>) body.get("authorities");
			
			Set<SimpleGrantedAuthority> simpleGrantedAuthorities =
					authorities.stream().map(auth-> new SimpleGrantedAuthority(auth.get("authority"))).collect(Collectors.toSet());
			
			Authentication authentication = new UsernamePasswordAuthenticationToken(userName, null, simpleGrantedAuthorities);
			
			SecurityContextHolder.getContext().setAuthentication(authentication);
			
		} catch (JwtException e) {
			throw new IllegalStateException(String.format("Token %s can not be trust", token));
		}
		
		filterChain.doFilter(request, response);
	}

}
