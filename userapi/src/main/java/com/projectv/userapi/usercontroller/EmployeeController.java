package com.projectv.userapi.usercontroller;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/employee")
public class EmployeeController {
	// To enable @PreAuthorize need to @EnableGlobalMethodSecurity(prePostEnabled = true) in configuration class 
	//hasRole('ROLE_') hasAnyRole('ROLE_') hasAuthority('permission') hasAnyAuthority('permission')
	
	@GetMapping(value = "/getemployee/user")
	@PreAuthorize("hasAnyRole('ROLE_USER')") 
	public String getUserDetails() {		
		return "This is User";
	}
	@GetMapping(value = "/getemployee/admin")
	@PreAuthorize("hasAnyRole('ROLE_ADMIN')") 
	public String getAdminDetails() {		
		return "This is Amdin";
	}
}
