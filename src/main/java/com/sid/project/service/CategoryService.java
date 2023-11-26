package com.sid.project.service;

import java.util.List;

import com.sid.project.entity.SpaCategory;

public interface CategoryService {
	 
	public void createCategory();
	
	public SpaCategory findById(String category);
	
	public void deleteById(String category);
	
	public List<SpaCategory> findAll();
	
	public SpaCategory save(SpaCategory category);
}
