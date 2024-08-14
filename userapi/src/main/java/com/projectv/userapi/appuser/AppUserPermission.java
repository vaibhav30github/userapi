package com.projectv.userapi.appuser;

public enum AppUserPermission {
	GET("GET"),
	POST("POST"),
	UPDATE("UPDATE"),
	DELETE("DELETE");
	
	private final String permission;

	private AppUserPermission(String permission) {
		this.permission = permission;
	}

	public String getPermission() {
		return permission;
	}
	
}
