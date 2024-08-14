package com.projectv.userapi.registation;

import java.util.Arrays;
import java.util.List;

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


	public String register(RegistrationRequest request) throws Exception {
		
		// need to impliment email validation
		AppUser appUser= null;
		if (validaateRequest(request)) {
			appUser =
					new AppUser(request.getFirstName(), request.getLastName(), request.getEmail(), request.getPassword(), request.getRoll());
		} else {
			throw new Exception("Request is not valid");
		}
		
		return appUserService.signUpUser(appUser);
	}
	
	boolean validaateRequest(RegistrationRequest request) {
		if (request.getFirstName().isBlank() || request.getFirstName().isEmpty()) {
			return false;
		}
		if (request.getLastName().isBlank() || request.getLastName().isEmpty()) {
			return false;
		}
		if (request.getEmail().isBlank() || request.getEmail().isEmpty()) {
			return false;
		}
		if (request.getPassword().isBlank() || request.getPassword().isEmpty()) {
			return false;
		}
		List<AppUserRole> rolls = Arrays.asList(AppUserRole.values());
		if (!rolls.contains(request.getRoll())) {
			return false;
		}
	
		
		return true;
	}

}
