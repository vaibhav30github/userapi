package com.projectv.userapi.registation;

import org.springframework.stereotype.Service;

import com.projectv.userapi.appuser.AppUser;
import com.projectv.userapi.appuser.AppUserRole;
import com.projectv.userapi.appuser.AppUserService;

@Service
public class RegistrationService {
	
	private final AppUserService appUserService;

	public RegistrationService(AppUserService appUserService) {
		super();
		this.appUserService = appUserService;
	}


	public String register(RegistrationRequest request) {
		
		// need to impliment email validation
		AppUser appUser =
				new AppUser(request.getFirstName(), request.getLastName(), request.getEmail(), request.getPassword(), AppUserRole.ADMIN);
		return appUserService.signUpUser(appUser);
	}

}
