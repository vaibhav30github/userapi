package com.projectv.userapi.usercontroller;

public class Employee {
	
	private Long id;
	private String name;
	private String roll;
	private long mobile;
	private String address;
	
	public Employee() {}
	

	public Employee(Long id, String name, String roll, long mobile, String address) {
		super();
		this.id = id;
		this.name = name;
		this.roll = roll;
		this.mobile = mobile;
		this.address = address;
	}


	public String getRoll() {
		return roll;
	}
	public void setRoll(String roll) {
		this.roll = roll;
	}
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public long getMobile() {
		return mobile;
	}
	public void setMobile(long mobile) {
		this.mobile = mobile;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
}
