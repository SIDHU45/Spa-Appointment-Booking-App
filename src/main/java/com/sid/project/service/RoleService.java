package com.sid.project.service;

import com.sid.project.entity.UserRole;

public interface RoleService {

	public UserRole findById(int id);
	
	public void deleteById(int id);
	
	public UserRole saveRole(UserRole userRole);
	
	public void createRole();
}
