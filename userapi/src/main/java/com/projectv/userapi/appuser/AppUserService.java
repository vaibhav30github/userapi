package com.projectv.userapi.appuser;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AppUserService implements UserDetailsService {
	
	private static final String USER_NOT_FOUND_MSG = "user with email %s not found";

	private final AppUserRepository appUserRepository;
	private final BCryptPasswordEncoder bCryptPasswordEncoder;
	

	public AppUserService(AppUserRepository appUserRepository, BCryptPasswordEncoder bCryptPasswordEncoder) {
		this.appUserRepository = appUserRepository;
		this.bCryptPasswordEncoder = bCryptPasswordEncoder;
	}
	@Override
	public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
		return appUserRepository.findByEmail(email).orElseThrow(() -> new UsernameNotFoundException(
				String.format(USER_NOT_FOUND_MSG, email)));
	}

	public String signUpUser(AppUser appUser) {
		
		boolean userExist = appUserRepository.findByEmail(appUser.getEmail()).isPresent();
		if(userExist) {
			throw new IllegalArgumentException("email already exit");
		}
		String encodePassword = bCryptPasswordEncoder.encode(appUser.getPassword());
		appUser.setPassword(encodePassword);
		
		AppUser savedUser = appUserRepository.save(appUser);
		
		return "User registered : ID = "+savedUser.getId();
	}
}
