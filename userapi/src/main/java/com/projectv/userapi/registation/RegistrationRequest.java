package com.projectv.userapi.registation;


public class RegistrationRequest {
	
	private  String firstName;
	private  String lastName;
	private  String email;
	private  String password;
	
	public RegistrationRequest() {}
	
	public RegistrationRequest(String firstName, String lastName, String email, String password) {

		this.firstName = firstName;
		this.lastName = lastName;
		this.email = email;
		this.password = password;
	}
	
	
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}


	public void setLastName(String lastName) {
		this.lastName = lastName;
	}


	public void setEmail(String email) {
		this.email = email;
	}


	public void setPassword(String password) {
		this.password = password;
	}


	public String getFirstName() {
		return firstName;
	}
	public String getLastName() {
		return lastName;
	}
	public String getEmail() {
		return email;
	}
	public String getPassword() {
		return password;
	}
	@Override
	public String toString() {
		return "RegistrationRequest [firstName=" + firstName + ", lastName=" + lastName + ", email=" + email
				+ ", password=" + password + "]";
	}
	@Override
	public int hashCode() {
		return super.hashCode();
	}
	@Override
	public boolean equals(Object obj) {
		if(obj instanceof RegistrationRequest) {
			RegistrationRequest registrationRequest = (RegistrationRequest) obj;
			if(this.getEmail().equals(registrationRequest.getEmail())) {
				return true;
			}
		}
		return false;
	}
	

}
