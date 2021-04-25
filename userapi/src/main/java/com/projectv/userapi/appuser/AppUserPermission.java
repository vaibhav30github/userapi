package com.projectv.userapi.appuser;

public enum AppUserPermission {
	GET("get"),
	POST("post"),
	UPDATE("update"),
	DELETE("delete");
	
	private final String permission;

	private AppUserPermission(String permission) {
		this.permission = permission;
	}

	public String getPermission() {
		return permission;
	}
	
}
