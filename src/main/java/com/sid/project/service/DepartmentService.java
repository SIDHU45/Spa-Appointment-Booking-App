package com.sid.project.service;

import com.sid.project.entity.SpaDepartment;

public interface DepartmentService {
	
	public SpaDepartment save(SpaDepartment spaDepartment);
	
	public void createDepatment();
	
	public SpaDepartment findById(int id);
}
