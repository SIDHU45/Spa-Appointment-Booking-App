package com.sid.project.service;

import java.util.List;

import com.sid.project.entity.SpaStylists;

public interface StylistService {
	
	public List<SpaStylists> findAll();
	
	public SpaStylists findById(int id);
	
	public SpaStylists save(SpaStylists spaStylists);
	
	public void createStylist();
}
