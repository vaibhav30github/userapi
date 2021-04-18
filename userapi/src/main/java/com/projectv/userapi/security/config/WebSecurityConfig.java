package com.projectv.userapi.security.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import com.projectv.userapi.appuser.AppUserService;
import com.projectv.userapi.jwt.JwtTokenVerifierFilter;
import com.projectv.userapi.jwt.JwtUsernameAndPasswordAuthanticatorFilter;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class WebSecurityConfig extends WebSecurityConfigurerAdapter{

	private final AppUserService appUserService;
	public final BCryptPasswordEncoder bCryptPasswordEncoder;
	
	
	public WebSecurityConfig(com.projectv.userapi.appuser.AppUserService appUserService, BCryptPasswordEncoder bCryptPasswordEncoder) {
		this.appUserService = appUserService;
		this.bCryptPasswordEncoder = bCryptPasswordEncoder;
	}

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http
		.csrf().disable()
		.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
		.and()
		.addFilter(new JwtUsernameAndPasswordAuthanticatorFilter(authenticationManager()))
		.addFilterAfter(new JwtTokenVerifierFilter(), JwtUsernameAndPasswordAuthanticatorFilter.class)
		.authorizeRequests()
		.antMatchers("/api/v*/registration/**")
		.permitAll()
		.anyRequest()
		.authenticated();
	}

	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth
		.authenticationProvider(authenticationProvider());
	}

	
	@Bean
	public DaoAuthenticationProvider authenticationProvider() {
		DaoAuthenticationProvider provider = new DaoAuthenticationProvider();
		provider.setPasswordEncoder(bCryptPasswordEncoder);
		provider.setUserDetailsService(appUserService);
		return provider;
	}
}
