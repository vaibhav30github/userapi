package com.projectv.userapi.usercontroller;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
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
	public Employee getAdminDetails() {
		Employee employee = new Employee(10l, "Vaibhav Verma", "ADMIN", 9876543210l, "Noida sector 60");
		return employee;
	}
	
	@PutMapping(value = "/updateemployee/update")
	@PreAuthorize("hasAuthority('UPDATE')")
	public String updatetMethod() {
		return "Update method working for this user";
	}
	
	@DeleteMapping(value = "/deleteemployee/delete")
	@PreAuthorize("hasAuthority('DELETE')")
	public String deletetMethod() {
		return "Delete method working for this user";
	}
	
	@PostMapping(value = "/postemployee/post")
	@PreAuthorize("hasAuthority('POST')")
	public String postMethod() {
		return "Post method working for this user";
	}
	
	@GetMapping(value = "/getemployee/get")
	@PreAuthorize("hasAuthority('GET')")
	public String getMethod() {
		return "Get method working for this user";
	}
	
	
	
}
