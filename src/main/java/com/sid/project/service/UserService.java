package com.sid.project.service;

import com.sid.project.entity.User;
import java.util.*;

public interface UserService {
	
	public List<User> findAll();
	
	public User findById(String phn_number);
	public User save(User user);
	public void deleteById(String phn_number);
	
	public void createAdmin();
	
	public User createUser(User theUser);
}
