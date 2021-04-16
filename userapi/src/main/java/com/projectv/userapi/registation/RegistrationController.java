package com.projectv.userapi.registation;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.projectv.userapi.appuser.AppUser;

@RestController
@RequestMapping(value = "/api/v1/registration")
public class RegistrationController {

	private RegistrationService registrationService;

	public RegistrationController(RegistrationService registrationService) {
		this.registrationService = registrationService;
	}


	@PostMapping(value = "/register")
	public String register(@RequestBody RegistrationRequest request) {
		return registrationService.register(request);
	}

}
