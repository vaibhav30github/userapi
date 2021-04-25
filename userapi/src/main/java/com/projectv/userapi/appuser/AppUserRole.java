package com.projectv.userapi.appuser;

import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.security.core.authority.SimpleGrantedAuthority;

public enum AppUserRole {
	USER(Set.of(AppUserPermission.GET, AppUserPermission.POST)),
	ADMIN(Set.of(AppUserPermission.GET, AppUserPermission.POST,AppUserPermission.UPDATE,AppUserPermission.DELETE));
	
	
	private final Set<AppUserPermission> permissions;

	private AppUserRole(Set<AppUserPermission> permissions) {
		this.permissions = permissions;
	}

	public Set<AppUserPermission> getPermissions() {
		return permissions;
	}
	
	public Set<SimpleGrantedAuthority> getGrantedAuthorities(){
        Set<SimpleGrantedAuthority> permissions = getPermissions().stream()
                .map(permission -> new SimpleGrantedAuthority(permission.getPermission()))
                .collect(Collectors.toSet());
        permissions.add(new SimpleGrantedAuthority("ROLE_" + this.name()));
        return permissions;
	}
}
